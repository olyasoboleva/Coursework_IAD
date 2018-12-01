package service;

import entity.ProductsAndLocationEntity;

public interface ProductsAndLocationService {
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
}
