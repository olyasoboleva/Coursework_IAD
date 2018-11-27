package impl;

import entity.ShopEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ShopRepository;
import service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;

@Service("shopService")
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Transactional
    @Override
    public ShopEntity createProduct(ShopEntity product) {
        shopRepository.save(product);
        return product;
    }

    @Transactional
    @Override
    public boolean deleteProduct(ShopEntity product) {
        shopRepository.delete(product);
        return true;
    }

    @Transactional
    @Override
    public ShopEntity updateProduct(ShopEntity product) {
        shopRepository.save(product);
        return product;
    }
}
