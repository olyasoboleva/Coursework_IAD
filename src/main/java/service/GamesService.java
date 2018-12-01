package service;

import entity.GamesEntity;

public interface GamesService {
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
