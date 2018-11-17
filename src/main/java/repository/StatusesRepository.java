package repository;

import entity.StatusesEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatusesRepository extends CrudRepository<StatusesEntity, Integer> {
    StatusesEntity findStatusesEntityByStatusid(long statusid);
}
