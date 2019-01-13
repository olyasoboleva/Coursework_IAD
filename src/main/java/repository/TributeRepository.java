package repository;

import entity.Game;
import entity.Tribute;
import entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TributeRepository extends CrudRepository<Tribute, Integer>{
    /**
     * find tribute by id
     *
     * @param tributeId id
     * @return tribute
     */
    Tribute findTributeByTributeId(long tributeId);

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
     * @param game tributes of this game
     * @param x1 left limit
     * @param x2 right limit
     * @param y1 top limit
     * @param y2 bottom limit
     * @return list of tributes
     */
    List<Tribute> getTributesByGameAndLocationXBetweenAndLocationYIsBetween(Game game, int x1, int x2, int y1, int y2);
}
