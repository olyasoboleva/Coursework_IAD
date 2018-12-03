package impl;

import entity.Arena;
import entity.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ArenaRepository;
import service.ArenaService;
import org.springframework.beans.factory.annotation.Autowired;

@Service("arenaService")
public class ArenaServiceImpl implements ArenaService {

    private final ArenaRepository arenaRepository;

    @Autowired
    public ArenaServiceImpl(ArenaRepository arenaRepository) {
        this.arenaRepository = arenaRepository;
    }


    @Override
    public Arena getArenaById(int arenaId) {
        return arenaRepository.findArenaByArenaId(arenaId);
    }

    @Override
    public Arena getArenaByGame(Game game) {
        return arenaRepository.findArenaByGame(game);
    }

    @Transactional
    @Override
    public Arena createArena(Arena arena) {
        arenaRepository.save(arena);
        return arena;
    }

    @Transactional
    @Override
    public boolean deleteArena(Arena arena) {
        arenaRepository.delete(arena);
        return true;
    }

    @Transactional
    @Override
    public Arena updateArena(Arena arena) {
        arenaRepository.save(arena);
        return arena;
    }
}
