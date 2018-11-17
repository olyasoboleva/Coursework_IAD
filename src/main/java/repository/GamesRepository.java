package repository;

import entity.GamesEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface GamesRepository extends CrudRepository<GamesEntity, Integer> {
    GamesEntity findGamesEntityByGameid(int gameid);
    List<GamesEntity> getGamesEntitiesByArena(Integer arena);
    List<GamesEntity> getGamesEntitiesBySteward(Integer steward);
    List<GamesEntity> getGamesEntitiesByTypeofgame(boolean typeofgame);
    List<GamesEntity> getGamesEntitiesByStartdate(Date startdate);
}
