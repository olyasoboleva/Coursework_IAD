package service;

import entity.StatusesEntity;

public interface StatusesService {

    /**
     * It creates new status
     * @param status status
     * @return new status if it was created
     */
    StatusesEntity createStatus(StatusesEntity status);

    /**
     * It deletes status
     * @param status status
     * @return true if it was deleted
     */
    boolean deleteStatus(StatusesEntity status);

    /**
     * It updates the status
     * @param status status
     * @return the status if it was correctly updated
     */
    StatusesEntity updateStatuses(StatusesEntity status);

    /**
     * find status by id
     * @param statusId id
     * @return status
     */
    StatusesEntity getStatuseById(int statusId);

    /**
     * find status by name
     * @param name name
     * @return status
     */
    StatusesEntity getStatuseByName(String name);
}
