package service;

import controller.WebSocketController;
import entity.Game;
import entity.Hook;
import entity.Map;
import entity.Tribute;
import model.Message;
import model.TributeHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import repository.HookRepository;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class SchedulerService {

    @Autowired
    private GameService gameService;

    @Autowired
    private TributeService tributeService;

    @Autowired
    private HookService hookService;

    @Autowired
    private MapService mapService;

    @Autowired
    private WebSocketController webSocketController;

    Calendar today;
    List<Game> gamesToday;
    List<Tribute> tributesToday;

    @Scheduled(cron = "0 0 10 * * *")
    public void runGame(){
        today = new GregorianCalendar();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),0,0,0);
        gamesToday = gameService.getGameByStartDate(today);
        for (Game game: gamesToday){
            tributesToday = tributeService.getTributesByGame(game);
            game.setStatus("running");
            gameService.updateGame(game);
            webSocketController.gameEvent(new Message("Началась игра!", "", Message.Type.GAMESTART));
        }
    }

    @Scheduled(cron = "0 */2 10-23 * * *")
    public void decreaseTributesHunger(){
        if (tributesToday!=null) {
            for (Tribute tribute : tributesToday) {
                tribute.setHunger(tribute.getHunger() - 1);
                tribute.setThirst(tribute.getThirst() - 2);
                tributeService.updateTribute(tribute);
                webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(),tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
            }
        }
    }

    @Scheduled(cron = "0 */5 10-23 * * *")
    public void decreaseTributesHealth(){
        if (tributesToday!=null) {
            for (Tribute tribute : tributesToday) {
                tribute.setHealth(tribute.getHealth() - (1 - tribute.getHunger() / 100) * 2 - (1 - tribute.getThirst() / 100) * 2);
                tributeService.updateTribute(tribute);
                webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(),tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
            }
        }
    }

    @Scheduled(cron = "0 */30 10-23 * * *")
    public void createHook(){
        if (gamesToday.size()!=0) {
            Game game = gamesToday.get(0);
            int x, y;
            x = (int) (Math.random() * game.getArena().getArenaLength());
            y = (int) (Math.random() * game.getArena().getArenaWidth());
            Map map = mapService.getCell(game.getArena(),x,y);
            List<Hook> hooks = hookService.getHookByLocation(map.getLocation());
            if (hooks.size()!=0){
                Hook hook = hooks.get((int)(Math.random()*hooks.size()));
                List<Tribute> damagedTributes = tributeService.getTributeInArea(game, x, y, hook.getRadius());
                for (Tribute tribute: damagedTributes){
                    tribute.setHealth(tribute.getHealth()-hook.getDamage());
                    tributeService.updateTribute(tribute);
                    webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(), tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
                }
            }
        }
    }
}
