package impl;

import entity.PresentstotributesEntity;
import entity.ShopEntity;
import entity.TributesEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PresentstotributesRepository;
import repository.ShopRepository;
import service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("shopService")
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;
    private final PresentstotributesRepository presentstotributesRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, PresentstotributesRepository presentstotributesRepository) {
        this.shopRepository = shopRepository;
        this.presentstotributesRepository = presentstotributesRepository;
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
        List<PresentstotributesEntity> presents = presentstotributesRepository.getPresentstotributesEntityByTributesByTributeid(tribute);
        List<ShopEntity> allproducts = shopRepository.findAll();
        List<ShopEntity> tributePresents = new ArrayList<>();
        for (PresentstotributesEntity present : presents) {
            for (ShopEntity product : allproducts) {
                if (product.getProductid() == present.getProductid()) {
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
