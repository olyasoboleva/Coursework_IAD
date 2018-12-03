package repository;

import entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PresentsToTributeRepository extends CrudRepository<PresentsToTribute,Integer>{
    /**
     * find sending by id
     * @param sendingId id
     * @return present of tribute
     */
    PresentsToTribute findPresentsToTributeBySendingId(long sendingId);

    /**
     * find all presents from sender to tribute
     * @param sender sender
     * @param tribute tribute
     * @return list of presents
     */
    List<PresentsToTribute> getPresentsToTributesBySenderAndTribute(User sender, Tribute tribute);

    /**
     * find all presents from sender
     * @param sender sender
     * @return list of presents
     */
    List<PresentsToTribute> getPresentsToTributesBySender(User sender);

    /**
     * find all presents to tribute
     * @param tribute tribute
     * @return list of presents
     */
    List<PresentsToTribute> getPresentsToTributesByTribute(Tribute tribute);
}