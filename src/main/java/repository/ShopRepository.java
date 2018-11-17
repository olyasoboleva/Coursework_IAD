package repository;

import entity.ShopEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopRepository extends CrudRepository<ShopEntity, Integer> {
    ShopEntity findShopEntityByProductid(int productid);
    List<ShopEntity> findShopEntitiesByTypeofpresent(String typeofpresent);
}
