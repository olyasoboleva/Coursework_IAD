package impl;

import entity.Arena;
import entity.Location;
import entity.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.LocationRepository;
import repository.MapRepository;
import service.MapService;

import java.util.ArrayList;
import java.util.List;

@Service("mapService")
public class MapServiceImpl implements MapService {

    private final MapRepository mapRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public MapServiceImpl(MapRepository mapRepository, LocationRepository locationRepository) {
        this.mapRepository = mapRepository;
        this.locationRepository = locationRepository;
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

    @Override
    public Map updateMap(Map map){
        return mapRepository.save(map);
    }

    @Override
    public List<Map> createAllGameField(Arena arena) {
        List<Map> gameField = new ArrayList<>();
        Map curMap;
        long numLocations = locationRepository.count();
        int locationId;
        Location curLocation;
        Location mainLocation = arena.getMainLocation();
        for (int x=0;x<arena.getArenaLength();x++){
            for (int y=0;y<arena.getArenaWidth();y++){
                if (Math.random()<0.5){
                    curLocation = mainLocation;
                } else {
                    locationId = (int) (Math.random()*numLocations+1);
                    curLocation = locationRepository.findLocationByLocationId(locationId);
                }
                curMap = new Map(arena, curLocation, x, y);
                mapRepository.save(curMap);
                gameField.add(curMap);
            }
        }
        return gameField;
    }

    @Override
    public List<Map> getAllGameField(Arena arena) {
        return mapRepository.findMapsByArena(arena);
    }
}
