package repository;

import entity.PricesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PricesRepository extends CrudRepository<PricesEntity,Integer> {
    /**
     * find price by id
     * @param priceid id
     * @return price
     */
    PricesEntity findPricesEntityByPriceid(int priceid);
}
