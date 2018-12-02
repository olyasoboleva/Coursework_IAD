package impl;

import entity.ProductsAndLocationEntity;
import entity.ShopEntity;
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
    public ProductsAndLocationEntity getProductsAndLocationById(int applyingId) {
        return productsAndLocationRepository.findProductsAndLocationEntityByApplyingId(applyingId);
    }

    @Override
    public List<ProductsAndLocationEntity> getProductsAndLocationByProduct(ShopEntity product) {
        return productsAndLocationRepository.getProductsAndLocationEntitiesByProduct(product);
    }

    @Transactional
    @Override
    public ProductsAndLocationEntity createProductsAndLocation(ProductsAndLocationEntity productsandlocation) {
        productsAndLocationRepository.save(productsandlocation);
        return productsandlocation;
    }

    @Transactional
    @Override
    public boolean deleteProductsAndLocation(ProductsAndLocationEntity productsAndLocation) {
        productsAndLocationRepository.delete(productsAndLocation);
        return true;
    }

    @Transactional
    @Override
    public ProductsAndLocationEntity updateProductsAndLocation(ProductsAndLocationEntity productsAndLocation) {
        productsAndLocationRepository.save(productsAndLocation);
        return productsAndLocation;
    }
}
