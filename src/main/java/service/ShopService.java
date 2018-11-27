package service;

import entity.ShopEntity;

public interface ShopService {
    ShopEntity createProduct(ShopEntity product);
    boolean deleteProduct(ShopEntity product);
    ShopEntity updateProduct(ShopEntity product);
}
