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
     * @param gameId id
     * @return game
     */
    GamesEntity findGamesEntityByGameId(int gameId);

    /**
     * fine all steward's games, which start after date
     * @param steward steward
     * @param date start date
     * @return list of games
     */
    List<GamesEntity> getGamesEntitiesByStewardAndStartDateGreaterThan(UsersEntity steward, Date date);

    /**
     * find all games, which start on this date
     * @param startDate start date
     * @return list of games
     */
    List<GamesEntity> getGamesEntitiesByStartDate(Date startDate);
}
