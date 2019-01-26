package impl;

import controller.WebSocketController;
import entity.*;
import model.Message;
import model.TributeHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.HookRepository;
import service.GameProcessService;
import service.HookService;
import service.MapService;
import service.TributeService;

import java.util.List;

@Service("hookService")
public class HookServiceImpl implements HookService {

    private final HookRepository hookRepository;
    private final TributeService tributeService;
    private final WebSocketController webSocketController;
    private final MapService mapService;
    private final GameProcessService gameProcessService;

    @Autowired
    public HookServiceImpl(HookRepository hookRepository, TributeService tributeService, WebSocketController webSocketController, MapService mapService, GameProcessService gameProcessService) {
        this.hookRepository = hookRepository;
        this.tributeService = tributeService;
        this.webSocketController = webSocketController;
        this.mapService = mapService;
        this.gameProcessService = gameProcessService;
    }

    @Override
    public Hook createHook(Hook hook) {
        return hookRepository.save(hook);
    }

    @Override
    public Hook updateHook(Hook hook) {
        return hookRepository.save(hook);
    }

    @Override
    public boolean deleteHook(Hook hook) {
        hookRepository.delete(hook);
        return true;
    }

    @Override
    public List<Hook> getHookByLocation(Location location) {
        return hookRepository.getHooksByLocation(location);
    }

    @Override
    public Hook getHookById(Integer id) {
        return hookRepository.findHookByHookId(id);
    }

    @Override
    public boolean activateHook(Game game, Hook hook, int x, int y) {
        Map cell = mapService.getCell(game.getArena(), x, y);
        if (hook.getLocation().equals(cell.getLocation())) {
            List<Tribute> damagedTributes = tributeService.getTributeInArea(game, x, y, hook.getRadius());
            for (Tribute tribute : damagedTributes) {
                tributeService.getDamage(tribute, hook.getDamage());
                if (tribute.getHealth()<=0){
                    webSocketController.gameEvent(new Message(tribute.getUser().getNick()+", "+tribute.getUser().getDistrict().getName(),"", Message.Type.DEADTRIBUTE));
                    webSocketController.dropAllWeapon(tribute, x, y);
                }
                webSocketController.userGameEvent(new Message("Вы попали в ловушку " + hook.getName() + "!", tribute.getUser().getNick(), Message.Type.HOOK));
                tributeService.updateTribute(tribute);
                webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(), tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
            }
            gameProcessService.isGameOver(game);
            return true;
        } else return false;
    }

    @Override
    public Hook getHookByName(String name) {
        return hookRepository.getHookByName(name);
    }

    @Override
    public List<Hook> getAllHooks() {
        return (List<Hook>)hookRepository.findAll();
    }
}
