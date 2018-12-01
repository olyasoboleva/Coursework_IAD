package impl;

import entity.PresentsToTributesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PresentsToTributesRepository;
import service.PresentsToTributesService;

@Service("presentsToTributesService")
public class PresentsToTributesServiceImpl implements PresentsToTributesService {

    private final PresentsToTributesRepository presentsToTributesRepository;

    @Autowired
    public PresentsToTributesServiceImpl(PresentsToTributesRepository presentsToTributesRepository) {
        this.presentsToTributesRepository = presentsToTributesRepository;
    }

    @Transactional
    @Override
    public PresentsToTributesEntity createPresentsToTributes(PresentsToTributesEntity presentstotribute) {
        presentsToTributesRepository.save(presentstotribute);
        return presentstotribute;
    }
}
