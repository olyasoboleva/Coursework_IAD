package service;

import entity.PricesEntity;

public interface PricesService {
    PricesEntity createPrice(PricesEntity price);
    boolean deletePrices(PricesEntity price);
    PricesEntity updatePrices(PricesEntity price);
}
