package repository;

import entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresentstotributesRepository extends CrudRepository<PresentstotributesEntity,Integer>{
    PresentstotributesEntity findPresentstotributesEntityBySendingid(long sendingid);
    List<PresentstotributesEntity> getPresentstotributesEntityByUsersBySenderidAndTributesByTributeid(UsersEntity sender, TributesEntity tribute);
    List<PresentstotributesEntity> getPresentstotributesEntityByUsersBySenderid(UsersEntity sender);
    List<PresentstotributesEntity> getPresentstotributesEntityByTributesByTributeid(TributesEntity tribute);
}