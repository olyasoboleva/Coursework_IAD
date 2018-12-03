package impl;

import entity.Game;
import entity.User;
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
    public Game getGameById(int gameId) {
        return gamesRepository.findGameByGameId(gameId);
    }

    @Override
    public List<Game> getGameByStewardAndAfterDate(User steward, Date date) {
        return gamesRepository.getGamesByStewardAndStartDateGreaterThan(steward, date);
    }

    @Override
    public List<Game> getGameByStartDate(Date startDate) {
        return gamesRepository.getGamesByStartDate(startDate);
    }

    @Transactional
    @Override
    public Game createGame(Game game) {
        gamesRepository.save(game);
        return game;
    }

    @Transactional
    @Override
    public boolean deleteGame(Game game) {
        gamesRepository.delete(game);
        return true;
    }

    @Transactional
    @Override
    public Game updateGame(Game game) {
        gamesRepository.save(game);
        return game;
    }
}
