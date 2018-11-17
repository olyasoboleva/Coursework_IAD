package repository;

import entity.TributesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TributesRepository  extends CrudRepository<TributesEntity, Integer>{
    TributesEntity findTributesEntityByTributeid(long tributeid);
    List<TributesEntity> getTributesEntitiesByUserid(Integer userid);
    List<TributesEntity> getTributesEntitiesByStatus(String status);
    //TODO:нам надо искать по здоровью, чтобы менять статус тем, у кого 0 здоровье? или это триггер?

}
