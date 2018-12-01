package impl;

import entity.ProductsAndLocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductsAndLocationRepository;
import service.ProductsAndLocationService;

@Service("productsandlocationService")
public class ProductsAndLocationServiceImpl implements ProductsAndLocationService {

    private final ProductsAndLocationRepository productsAndLocationRepository;

    @Autowired
    public ProductsAndLocationServiceImpl(ProductsAndLocationRepository productsAndLocationRepository) {
        this.productsAndLocationRepository = productsAndLocationRepository;
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
}
