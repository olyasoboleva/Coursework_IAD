package repository;

import entity.Game;
import entity.Tribute;
import entity.Weapon;
import entity.WeaponsInGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeaponsInGameRepository extends CrudRepository<WeaponsInGame, Integer> {

    /**
     * Try to find all relations between tributes and theirs weapons
     * @param tribute tribute
     * @return links to tribute and his weapons
     */
    List<WeaponsInGame> getWeaponsInGamesByTribute(Tribute tribute);

    /**
     * check whether tribute has this weapon
     * @param tribute tribute
     * @param weapon weapon
     * @return weapon of tribute or null
     */
    WeaponsInGame getWeaponsInGameByTributeAndWeapon(Tribute tribute, Weapon weapon);

    /**
     * find weapons of game in some area
     * @param game game
     * @param tribute tribute
     * @return list of weapons
     */
    List<WeaponsInGame> getWeaponsInGamesByGameAndTribute(Game game, Tribute tribute);

    /**
     * find weapons of game in cell
     * @param game game
     * @param x x coordinate
     * @param y y coordinate
     * @param tribute tribute
     * @return list of weapons
     */
    List<WeaponsInGame> getWeaponsInGamesByGameAndLocationXAndLocationYAndTribute(Game game, int x, int y, Tribute tribute);

    WeaponsInGame getWeaponsInGameByTributeAndActive(Tribute tribute, boolean active);
}
