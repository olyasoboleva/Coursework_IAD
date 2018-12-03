package repository;

import entity.Game;
import entity.Tribute;
import entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TributesRepository  extends CrudRepository<Tribute, Integer>{
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
}
