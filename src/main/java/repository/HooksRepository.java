package repository;

import entity.HooksEntity;
import entity.LocationsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HooksRepository extends CrudRepository<HooksEntity, Integer> {
    /**
     * find hook by id
     * @param hookid id
     * @return hook
     */
    HooksEntity findHooksEntityByHookid(int hookid);

    /**
     * find all hooks for this location
     * @param location location
     * @return list of hooks
     */
    List<HooksEntity> getHooksEntitiesByLocation(LocationsEntity location);
}
