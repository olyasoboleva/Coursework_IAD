package service;

import entity.Status;

public interface StatusService {

    /**
     * It creates new status
     * @param status status
     * @return new status if it was created
     */
    Status createStatus(Status status);

    /**
     * It deletes status
     * @param status status
     * @return true if it was deleted
     */
    boolean deleteStatus(Status status);

    /**
     * It updates the status
     * @param status status
     * @return the status if it was correctly updated
     */
    Status updateStatuses(Status status);

    /**
     * find status by id
     * @param statusId id
     * @return status
     */
    Status getStatuseById(int statusId);

    /**
     * find status by name
     * @param name name
     * @return status
     */
    Status getStatuseByName(String name);
}
