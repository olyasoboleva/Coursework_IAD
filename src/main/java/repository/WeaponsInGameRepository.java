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
     * @param x1 left limit
     * @param x2 right limit
     * @param y1 top limit
     * @param y2 bottom limit
     * @param tribute tribute
     * @return list of weapons
     */
    List<WeaponsInGame> getWeaponsInGamesByGameAndLocationXBetweenAndLocationYBetweenAndTribute(Game game, int x1, int x2, int y1, int y2, Tribute tribute);
}
