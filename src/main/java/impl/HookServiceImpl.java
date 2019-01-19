package impl;

import controller.WebSocketController;
import entity.*;
import model.Message;
import model.TributeHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.HookRepository;
import service.HookService;
import service.MapService;
import service.TributeService;

import java.util.List;

@Service("hookService")
public class HookServiceImpl implements HookService {

    @Autowired
    private HookRepository hookRepository;
    @Autowired
    private TributeService tributeService;
    @Autowired
    private WebSocketController webSocketController;
    @Autowired
    private MapService mapService;

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
                tribute.setHealth(tribute.getHealth() - hook.getDamage());
                webSocketController.userGameEvent(new Message("Вы попали в ловушку " + hook.getName() + "!", tribute.getUser().getNick(), Message.Type.HOOK));
                tributeService.updateTribute(tribute);
                webSocketController.getHealth(new TributeHealth(tribute.getUser().getNick(), tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
            }
            return true;
        } else return false;
    }

    @Override
    public Hook getHookByName(String name) {
        return hookRepository.getHookByName(name);
    }
}
