package service;

import entity.Game;
import entity.Tribute;
import entity.Weapon;
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
     * It update relation between tribute and weapon
     * @param weaponInGame weapon and tribute
     * @return relation if it was created
     */
    WeaponsInGame updateWeaponsInGame(WeaponsInGame weaponInGame);

    /**
     * Try to find all relations between tributes and theirs weapons
     * @param tribute tribute
     * @return links to tribute and his weapons
     */
    List<WeaponsInGame> getWeaponsInGameByTribute(Tribute tribute);

    /**
     * find weapons without owner
     * @param game game
     * @return list of weapons in game
     */
    List<WeaponsInGame> getWeaponsInGameWithoutOwner(Game game);

    /**
     * generate weapons on game's arena
     * @param game game
     * @return list of weapons in game
     */
    List<WeaponsInGame> throwWeaponsOnArena(Game game);

    /**
     * find weapons of game in cell without user
     * @param game game
     * @param x x coodinate
     * @param y y coordinate
     * @return list of weapons
     */
    List<WeaponsInGame> getWeaponsInGameInCellWithoutOwner(Game game, int x, int y);

    List<WeaponsInGame> addFreeWeaponsToTribute(Tribute tribute);

    WeaponsInGame getActiveWeapon(Tribute tribute);

    void setActiveWeapon(Tribute tribute, Weapon weapon);
}
