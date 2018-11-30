package repository;

import entity.StatusesEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatusesRepository extends CrudRepository<StatusesEntity, Integer> {
    /**
     * find status by id
     * @param statusid id
     * @return status
     */
    StatusesEntity findStatusesEntityByStatusid(long statusid);
}
