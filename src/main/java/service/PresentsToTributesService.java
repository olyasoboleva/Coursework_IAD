package service;

import entity.PresentsToTributesEntity;
import entity.TributesEntity;
import entity.UsersEntity;

import java.util.List;

public interface PresentsToTributesService {

    /**
     * find sending by id
     * @param sendingId id
     * @return present of tribute
     */
    PresentsToTributesEntity getPresentsToTributeById(int sendingId);

    /**
     * find all presents from sender to tribute
     * @param sender sender
     * @param tribute tribute
     * @return list of presents
     */
    List<PresentsToTributesEntity> getPresentsToTributeBySenderAndTribute(UsersEntity sender, TributesEntity tribute);

    /**
     * find all presents from sender
     * @param sender sender
     * @return list of presents
     */
    List<PresentsToTributesEntity> getPresentsToTributeBySender(UsersEntity sender);

    /**
     * find all presents to tribute
     * @param tribute tribute
     * @return list of presents
     */
    List<PresentsToTributesEntity> getPresentsToTributeByTribute(TributesEntity tribute);

    /**
     * send present to tribute after checking sender cash
     * @param present present
     * @return present if enough money and null if not
     */
    PresentsToTributesEntity createPresentsToTributes(PresentsToTributesEntity present);

    /**
     * It updates the relation between tribute and his presents
     * @param present present
     * @return this relation if it was correctly updated
     */
    PresentsToTributesEntity updatePresentsToTributes(PresentsToTributesEntity present);

    /**
     * It deletes the relation between tribute and his presents
     * @param present present
     * @return this relation if it was successfully deleted
     */
    boolean deletePresentsToTributes(PresentsToTributesEntity present);
}
