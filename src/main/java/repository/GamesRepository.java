package repository;

import entity.Game;
import entity.User;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface GamesRepository extends CrudRepository<Game, Integer> {
    /**
     * find game by id
     * @param gameId id
     * @return game
     */
    Game findGameByGameId(int gameId);

    /**
     * find all steward's games which start after date
     * @param steward steward
     * @param date start date
     * @return list of games
     */
    List<Game> getGamesByStewardAndStartDateGreaterThan(User steward, Date date);

    /**
     * find all games which start on this date
     * @param startDate start date
     * @return list of games
     */
    List<Game> getGamesByStartDate(Date startDate);
}
