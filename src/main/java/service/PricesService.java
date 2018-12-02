package service;

import entity.PricesEntity;

public interface PricesService {
    /**
     * find price by id
     * @param priceId id
     * @return price
     */
    PricesEntity getPriceById(int priceId);

    /**
     * It creates new price
     * @param price price
     * @return new price if it was created
     */
    PricesEntity createPrice(PricesEntity price);

    /**
     * It deletes price
     * @param price price
     * @return true if it was correrctli deleted
     */
    boolean deletePrices(PricesEntity price);

    /**
     * It updates price
     * @param price price
     * @return price if it was correctly updated
     */
    PricesEntity updatePrices(PricesEntity price);
}
