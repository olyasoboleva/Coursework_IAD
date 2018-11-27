package service;

import entity.ProductsandlocationEntity;

public interface ProductsandlocationService {
    ProductsandlocationEntity createProductsAndLocation(ProductsandlocationEntity productsAndLocation);
    boolean deleteProductsAndLocation(ProductsandlocationEntity productsAndLocation);
}
