package repository;

import entity.Price;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price,Integer> {
    /**
     * find price by id
     * @param priceId id
     * @return price
     */
    Price findPriceByPriceId(int priceId);

    Price findPriceByName(String name);
}
