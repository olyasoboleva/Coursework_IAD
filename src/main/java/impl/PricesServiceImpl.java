package impl;

import entity.PricesEntity;
import org.springframework.stereotype.Service;
import repository.PricesRepository;
import service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;

@Service("pricesService")
public class PricesServiceImpl implements PricesService {

    final PricesRepository pricesRepository;

    @Autowired
    public PricesServiceImpl(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    public PricesEntity createPrice(PricesEntity price) {
        pricesRepository.save(price);
        return price;
    }

    @Override
    public boolean deletePrices(PricesEntity price) {
        pricesRepository.delete(price);
        return true;
    }

    @Override
    public PricesEntity updatePrices(PricesEntity price) {
        pricesRepository.save(price);
        return price;
    }
}
