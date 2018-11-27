package repository;

import entity.GamesEntity;
import entity.TributesEntity;
import entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TributesRepository  extends CrudRepository<TributesEntity, Integer>{
    TributesEntity findTributesEntityByTributeid(long tributeid);
    List<TributesEntity> getTributesEntitiesByUsersByUserid(UsersEntity user);
    List<TributesEntity> getTributesEntitiesByStatus(String status);
    List<TributesEntity> getTributesEntitiesByGamesByGameid(GamesEntity game);
}
