package repository;

import entity.Hook;
import entity.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HooksRepository extends CrudRepository<Hook, Integer> {
    /**
     * find hook by id
     * @param hookId id
     * @return hook
     */
    Hook findHookByHookId(int hookId);

    /**
     * find all hooks for this location
     * @param location location
     * @return list of hooks
     */
    List<Hook> getHooksByLocation(Location location);
}
