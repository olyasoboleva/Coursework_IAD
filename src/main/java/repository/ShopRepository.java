package repository;

import entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopEntity, Integer> {
    ShopEntity findShopEntityByProductid(int productid);
    List<ShopEntity> findShopEntitiesByTypeofpresent(String typeofpresent);


}
