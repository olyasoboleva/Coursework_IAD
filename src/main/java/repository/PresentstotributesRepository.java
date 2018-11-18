package repository;

import entity.PresentstotributesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresentstotributesRepository extends CrudRepository<PresentstotributesEntity,Integer>{
    PresentstotributesEntity findPresentstotributesEntityByProductid(int productid);
    List<PresentstotributesEntity> getPresentstotributesEntityByTributeidAndSenderid(long tributeid, int senderid);
    //FIXME:вот это вообще не надо через join писать?? хотя можно и так
    List<PresentstotributesEntity> getPresentstotributesEntityByTributeid(long tributeid);
    List<PresentstotributesEntity> getPresentstotributesEntityBySenderid(int senderid);
    List<PresentstotributesEntity> getPresentstotributesEntityBySendingid(long sendingid);

    //FIXME: блин, это в сервисе будет, потому что это не работает скорее всего
    @Query("select present from PresentstotributesEntity present join TributesEntity tribute where present.tributeid = tribute.tributeid")
    List<PresentstotributesEntity> findPresentsByTribute(@Param("tributeID")int tributeID);
}
