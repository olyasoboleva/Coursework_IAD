package repository;

import entity.PricesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PricesRepository extends CrudRepository<PricesEntity,Integer> {
    PricesEntity findPricesEntityByPriceid(int priceid);
}
