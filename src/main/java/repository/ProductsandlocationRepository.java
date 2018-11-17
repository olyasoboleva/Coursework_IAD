package repository;

import entity.ProductsandlocationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsandlocationRepository extends CrudRepository<ProductsandlocationEntity, Integer> {
    ProductsandlocationEntity findProductsandlocationEntityByApplyingid(int applyingid);
    List<ProductsandlocationEntity> getProductsandlocationEntitiesByProductid(int productid);
}
