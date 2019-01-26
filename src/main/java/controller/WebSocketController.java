package controller;

import entity.*;
import impl.PresentsToTributeServiceImpl;
import model.Battle;
import model.Message;
import model.TributeHealth;
import model.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import service.*;

import java.util.Calendar;
import java.util.List;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private GameProcessService gameProcessService;

    @Autowired
    private GameService gameService;

    @Autowired
    private TributeService tributeService;

    @Autowired
    private PresentsToTributeServiceImpl presentsToTributeService;

    @Autowired
    private WeaponsInGameService weaponsInGameService;

    @MessageMapping("/hungergames/move")
    public void moveTribute(@Payload Coordinates coordinates) {

        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Game game = gameService.getGameByStartDate(Calendar.getInstance());
        Tribute tribute = tributeService.getTributeByUserAndGame(user, game);
        coordinates.setNick(user.getNick());
        tributeService.moveTribute(tribute, coordinates.getX(), coordinates.getY());
        takeWeapon(tribute);
        messagingTemplate.convertAndSendToUser(coordinates.getNick(),"/queue/health",
                new TributeHealth(coordinates.getNick(), tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
        messagingTemplate.convertAndSend("/topic/tributesLocation", coordinates);
        if (tribute.getHealth()<=0){
            gameEvent(new Message(tribute.getUser().getNick()+", "+tribute.getUser().getDistrict().getName(),"", Message.Type.DEADTRIBUTE));
        }
    }

    @MessageMapping("/hungergames/battle")
    public void battle(@Payload Battle battle) {
        User attackingUser = userService.getUserByNick(battle.getAttacking());
        User defendingUser = userService.getUserByNick(battle.getDefending());
        Game game = gameService.getGameByStartDate(Calendar.getInstance());
        Tribute attackingTribute = tributeService.getTributeByUserAndGame(attackingUser, game);
        Tribute defendingTribute = tributeService.getTributeByUserAndGame(defendingUser, game);
        WeaponsInGame weaponsInGame = weaponsInGameService.getActiveWeapon(attackingTribute);
        Weapon weapon;
        int x = defendingTribute.getLocationX(), y = defendingTribute.getLocationY();
        if (weaponsInGame!=null) {
            weapon = weaponsInGame.getWeapon();
        } else weapon = null;

        gameProcessService.fight(attackingTribute, defendingTribute, weapon);

        messagingTemplate.convertAndSendToUser(battle.getDefending(),"/queue/health",
                new TributeHealth(battle.getDefending(), defendingTribute.getHealth(), defendingTribute.getHunger(), defendingTribute.getThirst()));
        if (defendingTribute.getHealth() <= 0){
            moveTribute(new Coordinates(defendingTribute.getUser().getNick(), defendingTribute.getLocationX(), defendingTribute.getLocationY()));
            gameEvent(new Message(defendingTribute.getUser().getNick()+", "+defendingTribute.getUser().getDistrict().getName(),"", Message.Type.DEADTRIBUTE));
            dropAllWeapon(defendingTribute, x, y);
        }

        tributeService.updateTribute(attackingTribute);
        tributeService.updateTribute(defendingTribute);
        gameProcessService.isGameOver(game);
    }

    public void sendPresent(User sender, Tribute tribute, Shop present, int quantity){
        PresentsToTribute presentInBag = presentsToTributeService.getPresentByProductAndTribute(present, tribute);
        PresentsToTribute presentsToTribute = new PresentsToTribute(present, tribute, sender, quantity);
        if (presentInBag==null) {
            presentsToTributeService.createPresentsToTributes(presentsToTribute);
            messagingTemplate.convertAndSendToUser(tribute.getUser().getNick(), "/queue/presents", presentsToTribute);
        } else {
            presentInBag.setQuantity(presentInBag.getQuantity() + quantity);
            presentsToTributeService.updatePresentsToTributes(presentInBag);
            messagingTemplate.convertAndSendToUser(tribute.getUser().getNick(), "/queue/presents", presentInBag);
        }
        userGameEvent(new Message(sender+" отправил(а) вам подарок - "+present.getName(),tribute.getUser().getNick(), Message.Type.PRESENT));

    }

    public void takeWeapon(Tribute tribute){
        List<WeaponsInGame> weaponsInGames = weaponsInGameService.addFreeWeaponsToTribute(tribute);
        if (weaponsInGames.size()!=0){
            messagingTemplate.convertAndSendToUser(tribute.getUser().getNick(), "/queue/weapons", weaponsInGames);
        }
        for (WeaponsInGame weaponInGame: weaponsInGames){
            messagingTemplate.convertAndSend("/topic/weaponsDelLocation", new Coordinates(weaponInGame.getWeaponInGameId().toString(), weaponInGame.getLocationX(), weaponInGame.getLocationY()));
        }
    }

    public void dropAllWeapon(Tribute tribute, int x, int y){
        List<WeaponsInGame> weaponsInGames = weaponsInGameService.getWeaponsInGameByTribute(tribute);
        for (WeaponsInGame weaponInGame: weaponsInGames){
            weaponInGame.setActive(false);
            weaponInGame.setTribute(null);
            weaponInGame.setLocationX(x);
            weaponInGame.setLocationY(y);
            messagingTemplate.convertAndSend("/topic/weaponsAddLocation", new Coordinates(weaponInGame.getWeaponInGameId().toString(), weaponInGame.getLocationX(), weaponInGame.getLocationY()));
        }
    }

    @MessageMapping("/hungergames/message")
    public void gameEvent(@Payload Message message) {
        messagingTemplate.convertAndSend("/topic/notification", message);
    }

    @MessageMapping("/hungergames/userMessage")
    public void userGameEvent(@Payload Message message) {
        messagingTemplate.convertAndSendToUser(message.getNick(),"/queue/notification", message);
    }

    @MessageMapping("/hungergames/health")
    public void getHealth(@Payload TributeHealth tributeHealth) {
        messagingTemplate.convertAndSendToUser(tributeHealth.getNick(),"/queue/health", tributeHealth);
    }
}
