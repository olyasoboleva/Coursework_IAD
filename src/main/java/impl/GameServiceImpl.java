package impl;

import entity.Game;
import entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.GameRepository;
import service.GameService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

@Service("gameService")
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game getGameById(int gameId) {
        return gameRepository.findGameByGameId(gameId);
    }

    @Override
    public List<Game> getGameByStewardAndAfterDate(User steward, Date date) {
        return gameRepository.getGamesByStewardAndStartDateGreaterThan(steward, date);
    }

    @Override
    public List<Game> getGameByStartDate(Date startDate) {
        return gameRepository.getGamesByStartDate(startDate);
    }

    @Transactional
    @Override
    public Game createGame(Game game) {
        gameRepository.save(game);
        return game;
    }

    @Transactional
    @Override
    public boolean deleteGame(Game game) {
        gameRepository.delete(game);
        return true;
    }

    @Transactional
    @Override
    public Game updateGame(Game game) {
        gameRepository.save(game);
        return game;
    }
}
