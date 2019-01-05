package impl;

import entity.PresentsToTribute;
import entity.Shop;
import entity.Tribute;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PresentsToTributeRepository;
import repository.ShopRepository;
import service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service("shopService")
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;
    private final PresentsToTributeRepository presentsToTributeRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, PresentsToTributeRepository presentsToTributeRepository) {
        this.shopRepository = shopRepository;
        this.presentsToTributeRepository = presentsToTributeRepository;
    }

    @Override
    public Shop getProductById(int productId) {
        return shopRepository.findShopByProductId(productId);
    }

    @Override
    public List<Shop> getProductsByTypeOfPresent(String type) {
        return shopRepository.findShopsByTypeOfPresent(type);
    }

    @Override
    public List<Shop> getProductsByOwner(Tribute tribute) {
        return shopRepository.findShopsByProductOwners(tribute);
    }

    //FIXME: не тестила
    @Transactional
    @Override
    public List<Shop> getAllPresentsOfTribute(Tribute tribute) {
        List<PresentsToTribute> presents = presentsToTributeRepository.getPresentsToTributesByTribute(tribute);
        List<Shop> allproducts = (List<Shop>) shopRepository.findAll();
        List<Shop> tributePresents = new ArrayList<>();
        for (Shop product : allproducts ) {
            for (PresentsToTribute present : presents) {
                if (product.equals(present.getProduct())) {
                    tributePresents.add(product);
                }

            }
        }
        return  tributePresents;
    }

    @Transactional
    @Override
    public Shop createProduct(Shop product) {
        shopRepository.save(product);
        return product;
    }

    @Transactional
    @Override
    public boolean deleteProduct(Shop product) {
        shopRepository.delete(product);
        return true;
    }

    @Transactional
    @Override
    public Shop updateProduct(Shop product) {
        shopRepository.save(product);
        return product;
    }

    @Override
    public List<Shop> getFullRange(){
        return (List<Shop>)shopRepository.findAll();
    }
}
