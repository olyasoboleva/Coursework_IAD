package repository;

import entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PresentsToTributesRepository extends CrudRepository<PresentsToTributesEntity,Integer>{
    /**
     * find sending by id
     * @param sendingId id
     * @return present of tribute
     */
    PresentsToTributesEntity findPresentsToTributesEntityBySendingId(long sendingId);

    /**
     * find all presents from sender to tribute
     * @param sender sender
     * @param tribute tribute
     * @return list of presents
     */
    List<PresentsToTributesEntity> getPresentsToTributesEntityByUsersBySenderidAndTributesByTributeid(UsersEntity sender, TributesEntity tribute);

    /**
     * find all presents from sender
     * @param sender sender
     * @return list of presents
     */
    List<PresentsToTributesEntity> getPresentsToTributesEntityByUsersBySenderid(UsersEntity sender);

    /**
     * find all presents to tribute
     * @param tribute tribute
     * @return list of presents
     */
    List<PresentsToTributesEntity> getPresentsToTributesEntityByTributesByTributeid(TributesEntity tribute);
}