package repository;

import entity.ProductsAndLocationEntity;
import entity.ShopEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsAndLocationRepository extends CrudRepository<ProductsAndLocationEntity, Integer> {
    /**
     * find appling by id
     * @param applyingId id
     * @return applying
     */
    ProductsAndLocationEntity findProductsAndLocationEntityByApplyingId(int applyingId);
    /**
     * find all applying of product
     * @param product product
     * @return list of applyings
     */
    List<ProductsAndLocationEntity> getProductsAndLocationEntitiesByProduct(ShopEntity product);
}
