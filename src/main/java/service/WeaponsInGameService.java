package service;

import entity.WeaponsInGameEntity;

public interface WeaponsInGameService {
    WeaponsInGameEntity createWeaponsInGame(WeaponsInGameEntity weaponInGame);
    boolean deleteWeaponsInGame(WeaponsInGameEntity weaponInGame);
}
