package repository;

import entity.ArenasEntity;
import entity.GamesEntity;
import entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface GamesRepository extends CrudRepository<GamesEntity, Integer> {
    /**
     * find game by id
     * @param gameid id
     * @return game
     */
    GamesEntity findGamesEntityByGameid(int gameid);

    /**
     * fine all steward's games, which start after date
     * @param steward steward
     * @param date start date
     * @return list of games
     */
    List<GamesEntity> getGamesEntitiesByUsersByStewardAndStartdateGreaterThan(UsersEntity steward, Date date);

    /**
     * find all games, which start on this date
     * @param startdate start date
     * @return list of games
     */
    List<GamesEntity> getGamesEntitiesByStartdate(Date startdate);
}
