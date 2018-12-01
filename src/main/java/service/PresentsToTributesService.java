package service;

import entity.PresentsToTributesEntity;

public interface PresentsToTributesService {

    /**
     * send present to tribute after checking sender cash
     * @param present present
     * @return present if enough money and null if not
     */
    PresentsToTributesEntity createPresentsToTributes(PresentsToTributesEntity present);
}
