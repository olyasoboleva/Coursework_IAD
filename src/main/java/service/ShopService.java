package service;

import entity.ShopEntity;
import entity.TributesEntity;

import java.util.List;

public interface ShopService {
    List<ShopEntity> getAllPresentsOfTribute(TributesEntity tributesEntity);
    ShopEntity createProduct(ShopEntity product);
    boolean deleteProduct(ShopEntity product);
    ShopEntity updateProduct(ShopEntity product);
}
