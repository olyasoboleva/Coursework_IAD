package repository;

import entity.Shop;
import entity.Tribute;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopRepository extends CrudRepository<Shop, Integer> {
    /**
     * find product by id
     * @param productId id
     * @return product
     */
    Shop findShopByProductId(int productId);

    /**
     * find products by type
     * @param type type of present
     * @return list of products
     */
    List<Shop> findShopsByTypeOfPresent(String type);

    /**
     * find all products of tribute
     * @param tribute tribute
     * @return list of products
     */
    List<Shop> findShopsByProductOwners(Tribute tribute);

    Shop findShopByName(String name);
}
