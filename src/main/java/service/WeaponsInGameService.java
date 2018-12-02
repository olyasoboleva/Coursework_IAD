package service;

import entity.TributesEntity;
import entity.WeaponsInGameEntity;

import java.util.List;

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

    /**
     * Try to find all relations between tributes and theirs weapons
     * @param tribute tribute
     * @return links to tribute and his weapons
     */
    List<WeaponsInGameEntity> getWeaponsInGameByTribute(TributesEntity tribute);
}
