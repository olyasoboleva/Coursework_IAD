package repository;

import entity.PresentstotributesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PresentstotributesRepository extends CrudRepository<PresentstotributesEntity,Integer>{
    PresentstotributesEntity findPresentstotributesEntityByProductid(int productid);
    List<PresentstotributesEntity> getPresentstotributesEntityByTributeidAndSenderid(long tributeid, int senderid);
    List<PresentstotributesEntity> getPresentstotributesEntityByTributeid(long tributeid);
    List<PresentstotributesEntity> getPresentstotributesEntityBySenderid(int senderid);
    List<PresentstotributesEntity> getPresentstotributesEntityBySendingid(long sendingid);
}
