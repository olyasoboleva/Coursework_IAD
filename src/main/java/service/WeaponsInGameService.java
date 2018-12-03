package service;

import entity.Tribute;
import entity.WeaponsInGame;

import java.util.List;

public interface WeaponsInGameService {
    /**
     * It creates relation between tribute and weapon
     * @param weaponInGame weapon and tribute
     * @return relation if it was created
     */
    WeaponsInGame createWeaponsInGame(WeaponsInGame weaponInGame);

    /**
     * It deleted relation between tribute and weapon
     * @param weaponInGame weapon and tribute
     * @return true if it was deleted
     */
    boolean deleteWeaponsInGame(WeaponsInGame weaponInGame);

    /**
     * Try to find all relations between tributes and theirs weapons
     * @param tribute tribute
     * @return links to tribute and his weapons
     */
    List<WeaponsInGame> getWeaponsInGameByTribute(Tribute tribute);
}
