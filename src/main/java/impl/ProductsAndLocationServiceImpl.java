package impl;

import entity.ProductsAndLocation;
import entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductsAndLocationRepository;
import service.ProductsAndLocationService;

import java.util.List;

@Service("productsAndLocationService")
public class ProductsAndLocationServiceImpl implements ProductsAndLocationService {

    private final ProductsAndLocationRepository productsAndLocationRepository;

    @Autowired
    public ProductsAndLocationServiceImpl(ProductsAndLocationRepository productsAndLocationRepository) {
        this.productsAndLocationRepository = productsAndLocationRepository;
    }

    @Override
    public ProductsAndLocation getProductsAndLocationById(int applyingId) {
        return productsAndLocationRepository.findProductsAndLocationByApplyingId(applyingId);
    }

    @Override
    public List<ProductsAndLocation> getProductsAndLocationByProduct(Shop product) {
        return productsAndLocationRepository.getProductsAndLocationsByProduct(product);
    }

    @Transactional
    @Override
    public ProductsAndLocation createProductsAndLocation(ProductsAndLocation productsandlocation) {
        productsAndLocationRepository.save(productsandlocation);
        return productsandlocation;
    }

    @Transactional
    @Override
    public boolean deleteProductsAndLocation(ProductsAndLocation productsAndLocation) {
        productsAndLocationRepository.delete(productsAndLocation);
        return true;
    }

    @Transactional
    @Override
    public ProductsAndLocation updateProductsAndLocation(ProductsAndLocation productsAndLocation) {
        productsAndLocationRepository.save(productsAndLocation);
        return productsAndLocation;
    }
}
