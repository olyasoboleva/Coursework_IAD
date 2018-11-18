package repository;

import entity.ProductsandlocationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsandlocationRepository extends CrudRepository<ProductsandlocationEntity, Integer> {
    ProductsandlocationEntity findProductsandlocationEntityByApplyingid(int applyingid);
    List<ProductsandlocationEntity> getProductsandlocationEntitiesByProductid(int productid);


    /**
     * Позволяет во время игры получить тип местности, для которого можно использовать этот подарок
     * @param productID поис по id
     * @return все такие связи
     */
    @Query("select location from ProductsandlocationEntity location join ShopEntity shop on(location.productid = shop.productid) where location.productid = :productID")
    List<ProductsandlocationEntity> getProductsandlocationyProductID(@Param("productID")int productID);
}
