package repository;

import entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresentstotributesRepository extends CrudRepository<PresentstotributesEntity,Integer>{
    /**
     * find sending by id
     * @param sendingid id
     * @return present of tribute
     */
    PresentstotributesEntity findPresentstotributesEntityBySendingid(long sendingid);

    /**
     * find all presents from sender to tribute
     * @param sender sender
     * @param tribute tribute
     * @return list of presents
     */
    List<PresentstotributesEntity> getPresentstotributesEntityByUsersBySenderidAndTributesByTributeid(UsersEntity sender, TributesEntity tribute);

    /**
     * find all presents from sender
     * @param sender sender
     * @return list of presents
     */
    List<PresentstotributesEntity> getPresentstotributesEntityByUsersBySenderid(UsersEntity sender);

    /**
     * find all presents to tribute
     * @param tribute tribute
     * @return list of presents
     */
    List<PresentstotributesEntity> getPresentstotributesEntityByTributesByTributeid(TributesEntity tribute);
}