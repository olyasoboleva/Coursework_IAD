package service;

import entity.Arena;
import entity.Map;

import java.util.List;

public interface MapService {

    /**
     * find cell of map by id
     * @param mapId id
     * @return cell of map
     */
    Map getMapById(int mapId);


    /**
     * find cell of game map by coordinates
     * @param arena arena where game takes place
     * @param x first coordinate
     * @param y second coordinate
     * @return cell of map
     */
    Map getCell(Arena arena, int x, int y);

    /**
     * find area of map by coordinates
     * @param arena arena where game takes place
     * @param x current coordinate x
     * @param y current coordinate y
     * @return list of cells
     */
    List<Map> getArea(Arena arena, int x, int y);

    /**
     * It creates new cell of map
     * @param map map
     * @return new map if it was saved to database
     */
    Map createMap(Map map);

    /**
     * It deletes all map's cells of arena
     * @param arena arena
     * @return true if map was deleted
     */
    void deleteAllMap(Arena arena);

    /**
     * It deletes the map
     * @param map map
     * @return true if map was deleted
     */
    boolean deleteMap(Map map);

    /**
     * It updates the cell of map
     * @param map map
     * @return new map if it was correctly updated in database
     */
    Map updateMap(Map map);
}
