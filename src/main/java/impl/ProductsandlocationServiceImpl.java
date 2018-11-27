package impl;

import entity.ProductsandlocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductsandlocationRepository;
import service.ProductsandlocationService;

@Service("productsandlocationService")
public class ProductsandlocationServiceImpl implements ProductsandlocationService {

    private final ProductsandlocationRepository productsandlocationRepository;

    @Autowired
    public ProductsandlocationServiceImpl(ProductsandlocationRepository productsandlocationRepository) {
        this.productsandlocationRepository = productsandlocationRepository;
    }

    @Transactional
    @Override
    public ProductsandlocationEntity createProductsAndLocation(ProductsandlocationEntity productsandlocation) {
        productsandlocationRepository.save(productsandlocation);
        return productsandlocation;
    }

    @Transactional
    @Override
    public boolean deleteProductsAndLocation(ProductsandlocationEntity productsAndLocation) {
        productsandlocationRepository.delete(productsAndLocation);
        return true;
    }
}
