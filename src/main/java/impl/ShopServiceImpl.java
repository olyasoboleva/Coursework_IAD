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

    //TODO: ну я вроде написала это?
    /**
     * Запрос получает все подарки трибута
     * @param tribute трибут
     * @return подарки
     */
    @Transactional
    @Override
    public List<ShopEntity> getAllPresentsOfTribute(TributesEntity tribute) {
        List<PresentsToTributesEntity> presents = presentsToTributesRepository.getPresentsToTributesEntityByTributesByTributeid(tribute);
        List<ShopEntity> allproducts = shopRepository.findAll();
        List<ShopEntity> tributePresents = new ArrayList<>();
        for (PresentsToTributesEntity present : presents) {
            for (ShopEntity product : allproducts) {
                //TODO: тут что-то сломалось из-за отсутствия теперь ид)))
                /*
                if (product.getProductId() == present.getProductId()) {
                    tributePresents.add(product);
                }
                */
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
