package repository;

import entity.GamesEntity;
import entity.TributesEntity;
import entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TributesRepository  extends CrudRepository<TributesEntity, Integer>{
    /**
     * find tribute by id
     *
     * @param tributeId id
     * @return tribute
     */
    TributesEntity findTributesEntityByTributeId(long tributeId);

    /**
     * find all tributes of user
     *
     * @param user user
     * @return list of tributes
     */
    List<TributesEntity> getTributesEntitiesByUser(UsersEntity user);

    /**
     * find all tributes of game
     *
     * @param game game
     * @return list of tributes
     */
    List<TributesEntity> getTributesEntitiesByGame(GamesEntity game);

    /**
     * find all tributes of game with this status
     *
     * @param status status
     * @param game game
     * @return list of games
     */
    List<TributesEntity> getTributesEntityByStatusAndGame(String status, GamesEntity game);
}
