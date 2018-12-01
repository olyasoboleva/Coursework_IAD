package service;

import entity.StatusesEntity;

public interface StatusesService {

    /**
     * It updates the status
     * @param status status
     * @return the status if it was correctly updated
     */
    StatusesEntity updateStatuses(StatusesEntity status);
}
