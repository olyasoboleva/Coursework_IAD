package repository;

import entity.StatusesEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatusesRepository extends CrudRepository<StatusesEntity, Integer> {
    /**
     * find status by id
     * @param statusId id
     * @return status
     */
    StatusesEntity findStatusesEntityByStatusId(int statusId);

    /**
     * find status by name
     * @param name name
     * @return status
     */
    StatusesEntity findStatusesEntityByName(String name);
}
