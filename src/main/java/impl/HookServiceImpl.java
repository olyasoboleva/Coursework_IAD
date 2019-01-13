package impl;

import entity.Hook;
import entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.HookRepository;
import service.HookService;

import java.util.List;

@Service("hookService")
public class HookServiceImpl implements HookService {

    @Autowired
    private HookRepository hookRepository;

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
}
