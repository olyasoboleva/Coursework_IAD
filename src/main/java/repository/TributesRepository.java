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
     * @param tributeid id
     * @return tribute
     */
    TributesEntity findTributesEntityByTributeid(long tributeid);

    /**
     * find all tributes of user
     *
     * @param user user
     * @return list of tributes
     */
    List<TributesEntity> getTributesEntitiesByUsersByUserid(UsersEntity user);

    /**
     * find all tributes of game
     *
     * @param game game
     * @return list of tributes
     */
    List<TributesEntity> getTributesEntitiesByGamesByGameid(GamesEntity game);

    /**
     * find all tributes of game with this status
     *
     * @param status status
     * @param game game
     * @return list of games
     */
    List<TributesEntity> getTributesEntityByStatusAndGamesByGameid(String status, GamesEntity game);
}
