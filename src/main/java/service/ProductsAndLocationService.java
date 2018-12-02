package service;

import entity.ProductsAndLocationEntity;
import entity.ShopEntity;

import java.util.List;

public interface ProductsAndLocationService {
    /**
     * find applying by id
     * @param applyingId id
     * @return applying
     */
    ProductsAndLocationEntity getProductsAndLocationById(int applyingId);
    /**
     * find all applying of product
     * @param product product
     * @return list of applyings
     */
    List<ProductsAndLocationEntity> getProductsAndLocationByProduct(ShopEntity product);

    /**
     * It creates new relation between product ans location
     * @param productsAndLocation relation
     * @return this relation if it was saved
     */
    ProductsAndLocationEntity createProductsAndLocation(ProductsAndLocationEntity productsAndLocation);

    /**
     * It deleted the relation
     * @param productsAndLocation relation
     * @return true if it was deleted
     */
    boolean deleteProductsAndLocation(ProductsAndLocationEntity productsAndLocation);

    /**
     * It updated the relation between product and its location
     * @param productsAndLocation product and its location
     * @return this relation if it was updated correctly
     */
    ProductsAndLocationEntity updateProductsAndLocation(ProductsAndLocationEntity productsAndLocation);
}
