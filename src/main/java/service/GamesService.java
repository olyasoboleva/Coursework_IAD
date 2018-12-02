package service;

import entity.GamesEntity;
import entity.UsersEntity;

import java.sql.Date;
import java.util.List;

public interface GamesService {

    /**
     * find game by id
     * @param gameId id
     * @return game
     */
    GamesEntity getGameById(int gameId);

    /**
     * find all steward's games which start after date
     * @param steward steward
     * @param date start date
     * @return list of games
     */
    List<GamesEntity> getGameByStewardAndAfterDate(UsersEntity steward, Date date);

    /**
     * find all games which start on this date
     * @param startDate start date
     * @return list of games
     */
    List<GamesEntity> getGameByStartDate(Date startDate);

    /**
     * It creates new game
     * @param game game
     * @return new game if it was saved to database
     */
    GamesEntity createGame(GamesEntity game);

    /**
     * It deletes the game
     * @param game game
     * @return true if it was deleted correctly
     */
    boolean deleteGame(GamesEntity game);

    /**
     * It updates the game
     * @param game game
     * @return game if it was updated correctly
     */
    GamesEntity updateGame(GamesEntity game);
}
