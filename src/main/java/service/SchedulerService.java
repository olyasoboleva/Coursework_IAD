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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class SchedulerService {

    private final GameService gameService;

    private final TributeService tributeService;

    private final HookService hookService;

    private final MapService mapService;

    private final WebSocketController webSocketController;

    private final WeaponsInGameService weaponsInGameService;

    private List<Game> gamesToday;
    private List<Tribute> tributesToday;

    @Autowired
    public SchedulerService(GameService gameService, TributeService tributeService, HookService hookService, MapService mapService, WebSocketController webSocketController, WeaponsInGameService weaponsInGameService) {
        this.gameService = gameService;
        this.tributeService = tributeService;
        this.hookService = hookService;
        this.mapService = mapService;
        this.webSocketController = webSocketController;
        this.weaponsInGameService = weaponsInGameService;
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void prepareMap(){
        init();
        for (Game game: gamesToday){
            mapService.createAllGameField(game.getArena());
            weaponsInGameService.throwWeaponsOnArena(game);
        }
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void runGame(){
        init();
        for (Game game: gamesToday){
            game.setStatus("running");
            gameService.updateGame(game);
            webSocketController.gameEvent(new Message("Началась игра!", "", Message.Type.GAMESTART));
        }
    }

    @Scheduled(cron = "0 */2 10-23 * * *")
    public void decreaseTributesHunger(){
        if (tributesToday==null){
            init();
        }
        if (tributesToday.size()!=0) {
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
        if (tributesToday==null){
            init();
        }
        if (tributesToday.size()!=0) {
            for (Tribute tribute : tributesToday) {
                tribute.setHealth(tribute.getHealth() - (1 - tribute.getHunger() / 100) * 2 - (1 - tribute.getThirst() / 100) * 2);
                tributeService.updateTribute(tribute);
                webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(),tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
            }
        }
    }

    @Scheduled(cron = "0 */30 10-23 * * *")
    public void createHook(){
        if (gamesToday==null){
            init();
        }
        if (gamesToday.size()!=0) {
            Game game = gamesToday.get(0);
            int x, y;
            x = (int) (Math.random() * game.getArena().getArenaLength());
            y = (int) (Math.random() * game.getArena().getArenaWidth());
            Map map = mapService.getCell(game.getArena(),x,y);
            if (map!=null) {
                List<Hook> hooks = hookService.getHookByLocation(map.getLocation());
                if (hooks.size() != 0) {
                    Hook hook = hooks.get((int) (Math.random() * hooks.size()));
                    List<Tribute> damagedTributes = tributeService.getTributeInArea(game, x, y, hook.getRadius());
                    for (Tribute tribute : damagedTributes) {
                        tribute.setHealth(tribute.getHealth() - hook.getDamage());
                        webSocketController.userGameEvent(new Message("Вы попали в ловушку " + hook.getName() + "!", tribute.getUser().getNick(), Message.Type.HOOK));
                        tributeService.updateTribute(tribute);
                        webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(), tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
                    }
                }
            }
        }
    }

    private void init(){
        Calendar today = new GregorianCalendar();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),0,0,0);
        gamesToday = gameService.getGameByStartDate(today);
        if (gamesToday.size()!=0) {
            tributesToday = tributeService.getTributesByGame(gamesToday.get(0));
        } else {
            tributesToday = new ArrayList<>();
        }
    }
}
