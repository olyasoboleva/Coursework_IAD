package impl;

import entity.Price;
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
    public Price getPriceById(int priceId) {
        return pricesRepository.findPriceByPriceId(priceId);
    }

    @Override
    public Price createPrice(Price price) {
        pricesRepository.save(price);
        return price;
    }

    @Override
    public boolean deletePrices(Price price) {
        pricesRepository.delete(price);
        return true;
    }

    @Override
    public Price updatePrices(Price price) {
        pricesRepository.save(price);
        return price;
    }
}
