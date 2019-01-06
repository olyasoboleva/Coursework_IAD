package service;

import entity.Game;
import entity.Tribute;
import entity.User;

import java.util.List;

public interface TributeService {
    /**
     * It creates new tribute
     * @param tribute tribute
     * @return new tribute if it was saved
     */
    Tribute createTribute(Tribute tribute);

    /**
     * It deletes tribute
     * @param tribute tribute
     * @return true if it was correctly deleted
     */
    boolean deleteTribute(Tribute tribute);

    /**
     * It updates the tribute
     * @param tribute tribute
     * @return tribute if it was updated correctly
     */
    Tribute updateTribute(Tribute tribute);

    /**
     * find tribute by id
     *
     * @param tributeId id
     * @return tribute
     */
    Tribute getTributeById(long tributeId);

    /**
     * find all tributes of user
     *
     * @param user user
     * @return list of tributes
     */
    List<Tribute> getTributesByUser(User user);

    /**
     * find all tributes of game
     *
     * @param game game
     * @return list of tributes
     */
    List<Tribute> getTributesByGame(Game game);

    /**
     * find all tributes of game with this status
     *
     * @param status status
     * @param game game
     * @return list of games
     */
    List<Tribute> getTributesByStatusAndGame(String status, Game game);


}
