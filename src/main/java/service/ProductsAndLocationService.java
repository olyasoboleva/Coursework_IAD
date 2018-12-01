package service;

import entity.ProductsAndLocationEntity;

public interface ProductsAndLocationService {
    ProductsAndLocationEntity createProductsAndLocation(ProductsAndLocationEntity productsAndLocation);
    boolean deleteProductsAndLocation(ProductsAndLocationEntity productsAndLocation);
}
