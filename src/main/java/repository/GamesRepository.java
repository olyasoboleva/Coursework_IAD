package repository;

import entity.ArenasEntity;
import entity.GamesEntity;
import entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface GamesRepository extends CrudRepository<GamesEntity, Integer> {
    GamesEntity findGamesEntityByGameid(int gameid);
    //а оно вообще надо при связи 1-1?
    GamesEntity getGamesEntityByArenasByArena(ArenasEntity arena);
    //по сути все игры распорядителя вряд ли нужны, надо только будущие и текущие, надо будет с датами помудрить (но возможно это уже сервис)
    List<GamesEntity> getGamesEntitiesByUsersBySteward(UsersEntity steward);
    List<GamesEntity> getGamesEntitiesByStartdate(Date startdate);
}
