package repository;

import entity.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    /**
     * find status by id
     * @param statusId id
     * @return status
     */
    Status findStatusByStatusId(int statusId);

    /**
     * find status by name
     * @param name name
     * @return status
     */
    Status findStatuseByName(String name);
}
