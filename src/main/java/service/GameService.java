package service;

import entity.Game;
import entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface GameService {

    /**
     * find game by id
     * @param gameId id
     * @return game
     */
    Game getGameById(int gameId);

    /**
     * find all steward's games which start after date
     * @param steward steward
     * @param date start date
     * @return list of games
     */
    List<Game> getGameByStewardAndAfterDate(User steward, Calendar date);

    /**
     * find all games which start on this date
     * @param startDate start date
     * @return list of games
     */
    List<Game> getGameByStartDate(Calendar startDate);

    List<Game> getAllGames();

    /**
     * It creates new game
     * @param game game
     * @return new game if it was saved to database
     */
    Game createGame(Game game);

    /**
     * It deletes the game
     * @param game game
     * @return true if it was deleted correctly
     */
    boolean deleteGame(Game game);

    /**
     * It updates the game
     * @param game game
     * @return game if it was updated correctly
     */
    Game updateGame(Game game);

}
