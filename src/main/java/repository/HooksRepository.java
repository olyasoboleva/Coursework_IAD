package repository;

import entity.HooksEntity;
import entity.LocationsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HooksRepository extends CrudRepository<HooksEntity, Integer> {
    /**
     * find hook by id
     * @param hookId id
     * @return hook
     */
    HooksEntity findHooksEntityByHookId(int hookId);

    /**
     * find all hooks for this location
     * @param location location
     * @return list of hooks
     */
    List<HooksEntity> getHooksEntitiesByLocation(LocationsEntity location);
}
