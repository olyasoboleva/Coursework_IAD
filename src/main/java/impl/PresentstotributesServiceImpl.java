package impl;

import entity.PresentstotributesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PresentstotributesRepository;
import service.PresentstotributesService;

@Service("presentsToTributesService")
public class PresentstotributesServiceImpl implements PresentstotributesService{

    private final PresentstotributesRepository presentstotributesRepository;

    @Autowired
    public PresentstotributesServiceImpl(PresentstotributesRepository presentstotributesRepository) {
        this.presentstotributesRepository = presentstotributesRepository;
    }

    @Transactional
    @Override
    public PresentstotributesEntity createPresentsToTributes(PresentstotributesEntity presentstotribute) {
        presentstotributesRepository.save(presentstotribute);
        return presentstotribute;
    }
}
