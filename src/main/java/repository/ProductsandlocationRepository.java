package repository;

import entity.ProductsandlocationEntity;
import entity.ShopEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsandlocationRepository extends CrudRepository<ProductsandlocationEntity, Integer> {
    ProductsandlocationEntity findProductsandlocationEntityByApplyingid(int applyingid);
    /**
     * Позволяет во время игры получить тип местности, для которого можно использовать этот подарок
     * @param product поиск по подарку
     * @return все такие связи
     */
    List<ProductsandlocationEntity> getProductsandlocationEntitiesByShopByProductid(ShopEntity product);
}
