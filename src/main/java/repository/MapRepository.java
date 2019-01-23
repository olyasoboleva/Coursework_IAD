package repository;

import entity.Arena;
import entity.Game;
import entity.Map;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MapRepository extends CrudRepository<Map, Integer> {

    /**
     * find cell of map by id
     * @param mapId id
     * @return cell of map
     */
    Map findMapByCellId(int mapId);


    /**
     * find cell of game map by coordinates
     * @param arena arena where game takes place
     * @param x first coordinate
     * @param y second coordinate
     * @return cell of map
     */
    Map findMapByArenaAndXCoordinateAndYCoordinate(Arena arena, int x, int y);

    /**
     * find area of map by coordinates
     * @param arena arena where game takes place
     * @param x1 left limit
     * @param x2 right limit
     * @param y1 top limit
     * @param y2 bottom limit
     * @return list of cells
     */
    List<Map> findMapsByArenaAndXCoordinateBetweenAndYCoordinateBetween(Arena arena, int x1, int x2, int y1, int y2);

    /**
     * delete all cells of map after the end of the game
     * @param arena arena where game takes place
     */
    void deleteMapsByArena(Arena arena);

    List<Map> findMapsByArena(Arena arena);
}
