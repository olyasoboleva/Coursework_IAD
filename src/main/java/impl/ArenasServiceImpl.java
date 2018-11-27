package impl;

import entity.ArenasEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ArenasRepository;
import service.ArenasService;
import org.springframework.beans.factory.annotation.Autowired;

@Service("arenasService")
public class ArenasServiceImpl implements ArenasService {

    private final ArenasRepository arenasRepository;

    @Autowired
    public ArenasServiceImpl(ArenasRepository arenasRepository) {
        this.arenasRepository = arenasRepository;
    }

    @Transactional
    @Override
    public ArenasEntity createArena(ArenasEntity arena) {
        arenasRepository.save(arena);
        return arena;
    }

    @Transactional
    @Override
    public boolean deleteArena(ArenasEntity arena) {
        arenasRepository.delete(arena);
        return true;
    }

    @Transactional
    @Override
    public ArenasEntity updateArena(ArenasEntity arena) {
        arenasRepository.save(arena);
        return arena;
    }
}
