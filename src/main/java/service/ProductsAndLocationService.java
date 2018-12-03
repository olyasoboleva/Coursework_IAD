package service;

import entity.ProductsAndLocation;
import entity.Shop;

import java.util.List;

public interface ProductsAndLocationService {
    /**
     * find applying by id
     * @param applyingId id
     * @return applying
     */
    ProductsAndLocation getProductsAndLocationById(int applyingId);
    /**
     * find all applying of product
     * @param product product
     * @return list of applyings
     */
    List<ProductsAndLocation> getProductsAndLocationByProduct(Shop product);

    /**
     * It creates new relation between product ans location
     * @param productsAndLocation relation
     * @return this relation if it was saved
     */
    ProductsAndLocation createProductsAndLocation(ProductsAndLocation productsAndLocation);

    /**
     * It deleted the relation
     * @param productsAndLocation relation
     * @return true if it was deleted
     */
    boolean deleteProductsAndLocation(ProductsAndLocation productsAndLocation);

    /**
     * It updated the relation between product and its location
     * @param productsAndLocation product and its location
     * @return this relation if it was updated correctly
     */
    ProductsAndLocation updateProductsAndLocation(ProductsAndLocation productsAndLocation);
}
