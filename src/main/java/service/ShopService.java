package service;

import entity.ShopEntity;
import entity.TributesEntity;

import java.util.List;

public interface ShopService {
    /**
     * find product by id
     * @param productId id
     * @return product
     */
    ShopEntity getProductById(int productId);

    /**
     * find products by type
     * @param type type of present
     * @return list of products
     */
    List<ShopEntity> getProductsByTypeOfPresent(String type);

    /**
     * find all products of tribute
     * @param tribute tribute
     * @return list of products
     */
    List<ShopEntity> getProductsByOwner(TributesEntity tribute);

    /**
     * It finds all tributes presents
     * @param tribute tribute
     * @return list of products
     */
    List<ShopEntity> getAllPresentsOfTribute(TributesEntity tribute);

    /**
     * It creates new product
     * @param product product
     * @return shop if product was created
     */
    ShopEntity createProduct(ShopEntity product);

    /**
     * It deletes the product
     * @param product product
     * @return shop if product was successfully deleted
     */
    boolean deleteProduct(ShopEntity product);

    /**
     * It updates the shop
     * @param product product
     * @return shop if product was updated correctly
     */
    ShopEntity updateProduct(ShopEntity product);
}
