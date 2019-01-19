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
    private ShopServiceImpl shopService;

    @Autowired
    private PresentsToTributeServiceImpl presentsToTributeService;

    @MessageMapping("/hungergames/move")
    public void moveTribute(@Payload TributeLocation tributeLocation) {
        User user = userService.getUserByNick(tributeLocation.getNick());
        Game game = gameService.getGameById(tributeLocation.getGameId());
        Tribute tribute = tributeService.getTributeByUserAndGame(user, game);
        tributeService.moveTribute(tribute, tributeLocation.getX(), tributeLocation.getY());
        messagingTemplate.convertAndSendToUser(tributeLocation.getNick(),"/queue/health",
                new TributeHealth(tributeLocation.getNick(), tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
        messagingTemplate.convertAndSend("/topic/tributesLocation", tributeLocation);
    }

    @MessageMapping("hungergames/attack")
    public void attack(@Payload Battle battle) {
        messagingTemplate.convertAndSendToUser(battle.getDefending(),"/queue/attacks", battle);
    }

    @MessageMapping("/hungergames/battle")
    public void battle(@Payload Battle battle) {
        User attackingUser = userService.getUserByNick(battle.getAttacking());
        User defendingUser = userService.getUserByNick(battle.getDefending());
        Game game = gameService.getGameById(battle.getGameId());
        Tribute attackingTribute = tributeService.getTributeByUserAndGame(attackingUser, game);
        Tribute defendingTribute = tributeService.getTributeByUserAndGame(defendingUser, game);

        gameProcessService.fight(attackingTribute, defendingTribute, battle.getAttWeaponName(), battle.getDefWeaponName());

        if (attackingTribute.getHealth()<=0) {
            attackingTribute.setStatus("Убит");
            messagingTemplate.convertAndSend("/topic/deadTribute", attackingTribute);
            messagingTemplate.convertAndSendToUser(battle.getAttacking(),"/queue/health",
                    new TributeHealth(battle.getAttacking(), attackingTribute.getHealth(), attackingTribute.getHunger(), attackingTribute.getThirst()));
        }
        if (defendingTribute.getHealth()<=0){
            defendingTribute.setStatus("Убит");
            messagingTemplate.convertAndSend("/topic/deadTribute", defendingTribute);
            messagingTemplate.convertAndSendToUser(battle.getDefending(),"/queue/health",
                    new TributeHealth(battle.getDefending(), defendingTribute.getHealth(), defendingTribute.getHunger(), defendingTribute.getThirst()));
        }

        tributeService.updateTribute(attackingTribute);
        tributeService.updateTribute(defendingTribute);

        List<Tribute> tributesAlive = tributeService.getTributesByStatusAndGame("Жив", game);
        if (tributesAlive.size()==1){
            gameProcessService.changeStatusAfterEndOfTheGame(game, tributesAlive.get(0));
            gameEvent(new Message("Конец игры! Победитель - "+tributesAlive.get(0).getUser().getNick(),"", Message.Type.GAMEOVER));
        } else {
            if (tributesAlive.size()==0) {
                gameProcessService.changeStatusAfterEndOfTheGame(game, null);
                gameEvent(new Message("Конец игры! Но все умерли:)", "", Message.Type.GAMEOVER));
            }
        }
    }

    @MessageMapping("/hungergames/send_present")
    public void sendPresent(@Payload Integer tributeID, Integer presentID, int quantity){
        User sender = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = tributeService.getTributeById(tributeID);
        Shop present = shopService.getProductById(presentID);
        PresentsToTribute presentInBag = presentsToTributeService.getPresentByProductAndTribute(present, tribute);
        PresentsToTribute presentsToTribute = new PresentsToTribute(present, tribute, sender, quantity);
        if (presentsToTributeService.createPresentsToTributes(presentsToTribute)!=null){
            if (presentInBag==null) {
                messagingTemplate.convertAndSendToUser(tribute.getUser().getNick(), "/queue/presents", presentsToTribute);
            } else {
            presentInBag.setQuantity(presentInBag.getQuantity()+quantity);
            presentsToTributeService.updatePresentsToTributes(presentInBag);
            messagingTemplate.convertAndSendToUser(tribute.getUser().getNick(), "/queue/presents", presentInBag);
            }
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
