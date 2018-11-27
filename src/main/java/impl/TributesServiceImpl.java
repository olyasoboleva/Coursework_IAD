package impl;

import entity.PresentstotributesEntity;
import entity.ShopEntity;
import entity.TributesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PresentstotributesRepository;
import repository.ShopRepository;
import repository.TributesRepository;
import service.TributesService;

import java.util.List;
import java.util.stream.Collectors;

@Service("tributesService")
public class TributesServiceImpl implements TributesService {

    private final TributesRepository tributesRepository;

    @Autowired
    public TributesServiceImpl(TributesRepository tributesRepository) {
        this.tributesRepository = tributesRepository;
    }

    @Transactional
    @Override
    public TributesEntity createTribute(TributesEntity tribute) {
        tributesRepository.save(tribute);
        return tribute;
    }

    @Transactional
    @Override
    public boolean deleteTribute(TributesEntity tribute) {
        tributesRepository.delete(tribute);
        return true;
    }

    @Transactional
    @Override
    public TributesEntity updateTribute(TributesEntity tribute) {
        tributesRepository.save(tribute);
        return tribute;
    }
}
