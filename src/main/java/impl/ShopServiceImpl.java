package impl;

import entity.PresentsToTributesEntity;
import entity.ShopEntity;
import entity.TributesEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PresentsToTributesRepository;
import repository.ShopRepository;
import service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service("shopService")
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;
    private final PresentsToTributesRepository presentsToTributesRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, PresentsToTributesRepository presentsToTributesRepository) {
        this.shopRepository = shopRepository;
        this.presentsToTributesRepository = presentsToTributesRepository;
    }

    @Transactional
    @Override
    public List<ShopEntity> getAllPresentsOfTribute(TributesEntity tribute) {
        List<PresentsToTributesEntity> presents = presentsToTributesRepository.getPresentsToTributesEntityByTribute(tribute);
        List<ShopEntity> allproducts = shopRepository.findAll();
        List<ShopEntity> tributePresents = new ArrayList<>();
        for (ShopEntity product : allproducts ) {
            for (PresentsToTributesEntity present : presents) {
                if (product.equals(present.getProduct())) {
                    tributePresents.add(product);
                }

            }
        }
        return  tributePresents;
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
