package impl;

import entity.Arena;
import entity.Game;
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


    @Override
    public Arena getArenaById(int arenaId) {
        return arenasRepository.findArenaByArenaId(arenaId);
    }

    @Override
    public Arena getArenaByGame(Game game) {
        return arenasRepository.findArenaByGame(game);
    }

    @Transactional
    @Override
    public Arena createArena(Arena arena) {
        arenasRepository.save(arena);
        return arena;
    }

    @Transactional
    @Override
    public boolean deleteArena(Arena arena) {
        arenasRepository.delete(arena);
        return true;
    }

    @Transactional
    @Override
    public Arena updateArena(Arena arena) {
        arenasRepository.save(arena);
        return arena;
    }
}
