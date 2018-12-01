package repository;

import entity.ShopEntity;
import entity.TributesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopEntity, Integer> {
    /**
     * find product by id
     * @param productId id
     * @return product
     */
    ShopEntity findShopEntityByProductId(int productId);

    /**
     * find products by type
     * @param type type of present
     * @return list of products
     */
    List<ShopEntity> findShopEntitiesByTypeOfPresent(String type);

    /**
     * find all products of tribute
     * @param tribute tribute
     * @return list of products
     */
    List<ShopEntity> findShopEntitiesByProductOwners(TributesEntity tribute);
}
