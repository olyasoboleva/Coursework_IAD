package impl;

import entity.GamesEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.GamesRepository;
import service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;

@Service("gamesService")
public class GamesServiceImpl implements GamesService {

    private final GamesRepository gamesRepository;

    @Autowired
    public GamesServiceImpl(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @Transactional
    @Override
    public GamesEntity createGame(GamesEntity game) {
        gamesRepository.save(game);
        return game;
    }

    @Transactional
    @Override
    public boolean deleteGame(GamesEntity game) {
        gamesRepository.delete(game);
        return true;
    }

    @Transactional
    @Override
    public GamesEntity updateGame(GamesEntity game) {
        gamesRepository.save(game);
        return game;
    }
}
