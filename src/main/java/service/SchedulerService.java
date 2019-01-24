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

    private final GameProcessService gameProcessService;

    private Game gameToday;
    private List<Tribute> tributesToday;

    @Autowired
    public SchedulerService(GameService gameService, TributeService tributeService, HookService hookService, MapService mapService, WebSocketController webSocketController, WeaponsInGameService weaponsInGameService, GameProcessService gameProcessService) {
        this.gameService = gameService;
        this.tributeService = tributeService;
        this.hookService = hookService;
        this.mapService = mapService;
        this.webSocketController = webSocketController;
        this.weaponsInGameService = weaponsInGameService;
        this.gameProcessService = gameProcessService;
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void prepareMap(){
        init();
        if (gameToday!=null) {
            mapService.createAllGameField(gameToday.getArena());
            weaponsInGameService.throwWeaponsOnArena(gameToday);
        }
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void runGame(){
        init();
        if (gameToday!=null) {
            gameToday.setStatus("running");
            gameService.updateGame(gameToday);
            webSocketController.gameEvent(new Message("Начались " + gameToday.getGameId() + " Голодные игры", "", Message.Type.GAMESTART));
        }
    }

    //@Scheduled(cron = "0 */5 10-23 * * *")
    @Scheduled(cron = "0 */5 * * * *")
    public void decreaseTributesHunger(){
        if (tributesToday==null){
            init();
        }
        if (tributesToday.size()!=0) {
            for (Tribute tribute : tributesToday) {
                tributeService.getHunger(tribute, 1);
                tributeService.getThirst(tribute, 2);
                webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(),tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
            }
        }
    }

    //@Scheduled(cron = "0 */10 10-23 * * *")
    @Scheduled(cron = "0 */10 * * * *")
    public void decreaseTributesHealth(){
        int damage = 0;
        if (tributesToday==null){
            init();
        }
        if (tributesToday.size()!=0) {
            for (Tribute tribute : tributesToday) {
                damage = ((100 - tribute.getHunger()) * 2 + (100 - tribute.getThirst()) * 2)/10;
                tributeService.getDamage(tribute, damage);
                if (tribute.getHealth()<=0){
                    webSocketController.gameEvent(new Message(tribute.getUser().getNick()+", "+tribute.getUser().getDistrict().getName(),"", Message.Type.DEADTRIBUTE));
                    if (gameProcessService.isGameOver(gameToday)) tributesToday = new ArrayList<>();
                }
                webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(),tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
            }
        }
    }

    //@Scheduled(cron = "0 */30 10-23 * * *")
    @Scheduled(cron = "0 */30 * * * *")
    public void createHook(){
        if (gameToday==null){
            init();
        }
        if (gameToday!=null) {
            int x, y;
            x = (int) (Math.random() * gameToday.getArena().getArenaLength());
            y = (int) (Math.random() * gameToday.getArena().getArenaWidth());
            Map map = mapService.getCell(gameToday.getArena(),x,y);
            if (map!=null) {
                List<Hook> hooks = hookService.getHookByLocation(map.getLocation());
                if (hooks.size() != 0) {
                    Hook hook = hooks.get((int) (Math.random() * hooks.size()));
                    hookService.activateHook(gameToday,hook,x,y);
                }
            }
        }
    }

    private void init(){
        Calendar today = new GregorianCalendar();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),0,0,0);
        gameToday = gameService.getGameByStartDate(today);
        if (gameToday!=null) {
            tributesToday = tributeService.getTributesByGame(gameToday);
        } else {
            tributesToday = new ArrayList<>();
        }
    }
}
