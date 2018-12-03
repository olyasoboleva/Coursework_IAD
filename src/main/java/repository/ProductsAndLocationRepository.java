package repository;

import entity.ProductsAndLocation;
import entity.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsAndLocationRepository extends CrudRepository<ProductsAndLocation, Integer> {
    /**
     * find applying by id
     * @param applyingId id
     * @return applying
     */
    ProductsAndLocation findProductsAndLocationByApplyingId(int applyingId);
    /**
     * find all applying of product
     * @param product product
     * @return list of applyings
     */
    List<ProductsAndLocation> getProductsAndLocationsByProduct(Shop product);
}
