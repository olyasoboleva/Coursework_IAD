package repository;

import entity.Game;
import entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface GameRepository extends CrudRepository<Game, Integer> {
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
    List<Game> getGamesByStewardAndStartDateGreaterThan(User steward, Calendar date);

    /**
     * find game which start on this date
     * @param startDate start date
     * @return game
     */
    Game getGamesByStartDate(Calendar startDate);

    /**
     * get all games with status
     * @param status status
     * @return list of games
     */
    List<Game> getGamesByStatus(String status);

    List<Game> getGamesByStartDateBefore(Calendar startDate);
}
