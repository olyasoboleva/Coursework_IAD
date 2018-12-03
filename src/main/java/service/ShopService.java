package service;

import entity.Shop;
import entity.Tribute;

import java.util.List;

public interface ShopService {
    /**
     * find product by id
     * @param productId id
     * @return product
     */
    Shop getProductById(int productId);

    /**
     * find products by type
     * @param type type of present
     * @return list of products
     */
    List<Shop> getProductsByTypeOfPresent(String type);

    /**
     * find all products of tribute
     * @param tribute tribute
     * @return list of products
     */
    List<Shop> getProductsByOwner(Tribute tribute);

    /**
     * It finds all tributes presents
     * @param tribute tribute
     * @return list of products
     */
    List<Shop> getAllPresentsOfTribute(Tribute tribute);

    /**
     * It creates new product
     * @param product product
     * @return shop if product was created
     */
    Shop createProduct(Shop product);

    /**
     * It deletes the product
     * @param product product
     * @return shop if product was successfully deleted
     */
    boolean deleteProduct(Shop product);

    /**
     * It updates the shop
     * @param product product
     * @return shop if product was updated correctly
     */
    Shop updateProduct(Shop product);
}
