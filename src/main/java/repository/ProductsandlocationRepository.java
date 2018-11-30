package repository;

import entity.ProductsandlocationEntity;
import entity.ShopEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsandlocationRepository extends CrudRepository<ProductsandlocationEntity, Integer> {
    /**
     * find appling by id
     * @param applyingid id
     * @return applying
     */
    ProductsandlocationEntity findProductsandlocationEntityByApplyingid(int applyingid);
    /**
     * find all applying of product
     * @param product product
     * @return list of applyings
     */
    List<ProductsandlocationEntity> getProductsandlocationEntitiesByShopByProductid(ShopEntity product);
}
