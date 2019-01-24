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
    Tribute getTributeById(int tributeId);

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

    /**
     * find tribute of user in this game
     * @param user user
     * @param game game
     * @return tribute
     */
    Tribute getTributeByUserAndGame(User user, Game game);

    /**
     * find tributes in area
     * @param game game
     * @param x current x
     * @param y current y
     * @param radius radius of figure around current position
     * @return list of tributes
     */
    List<Tribute> getTributeInArea(Game game, int x, int y, int radius);

    void moveTribute(Tribute tribute, int newX, int newY);

    int getDamage(Tribute tribute, int damage);

    void getHunger(Tribute tribute, int hunger);

    void getThirst(Tribute tribute, int thirst);
}
