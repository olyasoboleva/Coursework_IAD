package controller;

import entity.*;
import impl.PresentsToTributeServiceImpl;
import impl.ShopServiceImpl;
import model.Battle;
import model.Message;
import model.TributeHealth;
import model.TributeLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import service.GameProcessService;
import service.GameService;
import service.TributeService;
import service.UserService;

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

    @MessageMapping("/hungergames/move")
    public void moveTribute(@Payload TributeLocation tributeLocation) {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Game game = gameService.getGameByStartDate(Calendar.getInstance());
        Tribute tribute = tributeService.getTributeByUserAndGame(user, game);
        tributeLocation.setNick(user.getNick());
        tributeService.moveTribute(tribute, tributeLocation.getX(), tributeLocation.getY());
        messagingTemplate.convertAndSendToUser(tributeLocation.getNick(),"/queue/health",
                new TributeHealth(tributeLocation.getNick(), tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
        messagingTemplate.convertAndSend("/topic/tributesLocation", tributeLocation);
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

        gameProcessService.fight(attackingTribute, defendingTribute, battle.getAttWeaponName());

        if (attackingTribute.getHealth() <=0 ) {
            attackingTribute.setStatus("Убит");
            gameEvent(new Message(attackingTribute.getUser().getNick()+", "+attackingTribute.getUser().getDistrict().getName(),"", Message.Type.DEADTRIBUTE));
            messagingTemplate.convertAndSendToUser(battle.getAttacking(),"/queue/health",
                    new TributeHealth(battle.getAttacking(), attackingTribute.getHealth(), attackingTribute.getHunger(), attackingTribute.getThirst()));
        }
        if (defendingTribute.getHealth() <= 0){
            defendingTribute.setStatus("Убит");
            gameEvent(new Message(defendingTribute.getUser().getNick()+", "+defendingTribute.getUser().getDistrict().getName(),"", Message.Type.DEADTRIBUTE));
            messagingTemplate.convertAndSendToUser(battle.getDefending(),"/queue/health",
                    new TributeHealth(battle.getDefending(), defendingTribute.getHealth(), defendingTribute.getHunger(), defendingTribute.getThirst()));
        }

        tributeService.updateTribute(attackingTribute);
        tributeService.updateTribute(defendingTribute);

        List<Tribute> tributesAlive = tributeService.getTributesByStatusAndGame("Жив", game);
        if (tributesAlive.size() == 1){
            gameProcessService.changeStatusAfterEndOfTheGame(game, tributesAlive.get(0));
            gameEvent(new Message("Конец игры! Победитель - " + tributesAlive.get(0).getUser().getNick(),"", Message.Type.GAMEOVER));
        } else {
            if (tributesAlive.size() == 0) {
                gameProcessService.changeStatusAfterEndOfTheGame(game, null);
                gameEvent(new Message("Конец игры! Но все умерли:)", "", Message.Type.GAMEOVER));
            }
        }
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
