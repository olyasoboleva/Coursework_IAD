package service;

import entity.Game;
import entity.Hook;
import entity.Location;

import java.util.List;

public interface HookService {

    Hook createHook(Hook hook);

    Hook updateHook(Hook hook);

    boolean deleteHook(Hook hook);

    List<Hook> getHookByLocation(Location location);

    Hook getHookById(Integer id);

    boolean activateHook(Game game, Hook hook, int x, int y);

    Hook getHookByName(String name);
}
