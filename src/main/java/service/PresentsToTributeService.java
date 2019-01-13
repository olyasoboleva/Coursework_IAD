package service;

import entity.PresentsToTribute;
import entity.Shop;
import entity.Tribute;
import entity.User;

import java.util.List;

public interface PresentsToTributeService {

    /**
     * find sending by id
     * @param sendingId id
     * @return present of tribute
     */
    PresentsToTribute getPresentsToTributeById(int sendingId);

    /**
     * find all presents from sender to tribute
     * @param sender sender
     * @param tribute tribute
     * @return list of presents
     */
    List<PresentsToTribute> getPresentsToTributeBySenderAndTribute(User sender, Tribute tribute);

    /**
     * find all presents from sender
     * @param sender sender
     * @return list of presents
     */
    List<PresentsToTribute> getPresentsToTributeBySender(User sender);

    /**
     * find all presents to tribute
     * @param tribute tribute
     * @return list of presents
     */
    List<PresentsToTribute> getPresentsToTributeByTribute(Tribute tribute);

    /**
     * send present to tribute after checking sender cash
     * @param present present
     * @return present if enough money and null if not
     */
    PresentsToTribute createPresentsToTributes(PresentsToTribute present);

    /**
     * It updates the relation between tribute and his presents
     * @param present present
     * @return this relation if it was correctly updated
     */
    PresentsToTribute updatePresentsToTributes(PresentsToTribute present);

    /**
     * It deletes the relation between tribute and his presents
     * @param present present
     * @return this relation if it was successfully deleted
     */
    boolean deletePresentsToTributes(PresentsToTribute present);

    /**
     * find present by product and tribute
     * @param product product
     * @param tribute tribute
     * @return present
     */
    PresentsToTribute getPresentByProductAndTribute(Shop product, Tribute tribute);
}
