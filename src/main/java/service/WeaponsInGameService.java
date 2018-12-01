package service;

import entity.WeaponsInGameEntity;

public interface WeaponsInGameService {
    /**
     * It creates relation between tribute and weapon
     * @param weaponInGame weapon and tribute
     * @return relation if it was created
     */
    WeaponsInGameEntity createWeaponsInGame(WeaponsInGameEntity weaponInGame);

    /**
     * It deleted relation between tribute and weapon
     * @param weaponInGame weapon and tribute
     * @return true if it was deleted
     */
    boolean deleteWeaponsInGame(WeaponsInGameEntity weaponInGame);
}
