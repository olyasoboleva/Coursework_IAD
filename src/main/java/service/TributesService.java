package service;

import entity.ShopEntity;
import entity.TributesEntity;

import java.util.List;

public interface TributesService {
    /**
     * It creates new tribute
     * @param tribute tribute
     * @return new tribute if it was saved
     */
    TributesEntity createTribute(TributesEntity tribute);

    /**
     * It deletes tribute
     * @param tribute tribute
     * @return true if it was correctly deleted
     */
    boolean deleteTribute(TributesEntity tribute);

    /**
     * It updates the tribute
     * @param tribute tribute
     * @return tribute if it was updated correctly
     */
    TributesEntity updateTribute(TributesEntity tribute);
}
