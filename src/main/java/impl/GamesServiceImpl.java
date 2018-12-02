package impl;

import entity.GamesEntity;
import entity.UsersEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.GamesRepository;
import service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

@Service("gamesService")
public class GamesServiceImpl implements GamesService {

    private final GamesRepository gamesRepository;

    @Autowired
    public GamesServiceImpl(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @Override
    public GamesEntity getGameById(int gameId) {
        return gamesRepository.findGamesEntityByGameId(gameId);
    }

    @Override
    public List<GamesEntity> getGameByStewardAndAfterDate(UsersEntity steward, Date date) {
        return gamesRepository.getGamesEntitiesByStewardAndStartDateGreaterThan(steward, date);
    }

    @Override
    public List<GamesEntity> getGameByStartDate(Date startDate) {
        return gamesRepository.getGamesEntitiesByStartDate(startDate);
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
