package service;

import entity.Price;

public interface PriceService {
    /**
     * find price by id
     * @param priceId id
     * @return price
     */
    Price getPriceById(int priceId);

    /**
     * find price by name
     * @param name name
     * @return price
     */
    Price getPriceByName(String name);

    /**
     * It creates new price
     * @param price price
     * @return new price if it was created
     */
    Price createPrice(Price price);

    /**
     * It deletes price
     * @param price price
     * @return true if it was correrctli deleted
     */
    boolean deletePrices(Price price);

    /**
     * It updates price
     * @param price price
     * @return price if it was correctly updated
     */
    Price updatePrices(Price price);
}
