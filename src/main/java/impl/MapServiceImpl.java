package impl;

import entity.Arena;
import entity.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MapRepository;
import service.MapService;

import java.util.List;

@Service("mapService")
public class MapServiceImpl implements MapService {

    private final MapRepository mapRepository;

    @Autowired
    public MapServiceImpl(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    @Override
    public Map getMapById(int mapId){
        return mapRepository.findMapByCellId(mapId);
    }

    @Override
    public Map getCell(Arena arena, int x, int y){
        return mapRepository.findMapByArenaAndXCoordinateAndYCoordinate(arena,x,y);
    }

    public List<Map> getArea(Arena arena, int x, int y){
        int mapSize = 9;
        int x1, x2, y1, y2;
        //FIXME: скорее всего надо исправить, когда игрок у границы карты
        x1 = (x < (mapSize-1)/2) ? 0 : x - (mapSize - 1)/2;
        y1 = (y < (mapSize-1)/2) ? 0 : y - (mapSize - 1)/2;
        x2 = (arena.getArenaLength()-1-x < (mapSize-1)/2) ? arena.getArenaLength()-1 : x + (mapSize - 1)/2;
        y2 = (arena.getArenaWidth()-1-y < (mapSize-1)/2) ? arena.getArenaWidth()-1 : y + (mapSize - 1)/2;
        return mapRepository.findMapsByArenaAndXCoordinateBetweenAndYCoordinateBetween(arena, x1, x2, y1, y2);
    }

    @Override
    public Map createMap(Map map){
        return mapRepository.save(map);
    }

    @Override
    public void deleteAllMap(Arena arena){
        mapRepository.deleteMapsByArena(arena);
    }

    @Override
    public boolean deleteMap(Map map){
        mapRepository.delete(map);
        return true;
    }

    public Map updateMap(Map map){
        return mapRepository.save(map);
    }
}
