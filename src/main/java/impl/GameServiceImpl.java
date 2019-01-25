package impl;

import entity.Game;
import entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.GameRepository;
import service.GameService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
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
    public List<Game> getGameByStewardAndAfterDate(User steward, Calendar date) {
        return gameRepository.getGamesByStewardAndStartDateGreaterThan(steward, date);
    }

    @Override
    public Game getGameByStartDate(Calendar startDate) {
        return gameRepository.getGamesByStartDate(startDate);
    }

    @Override
    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    @Transactional
    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
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
        return gameRepository.save(game);
    }

    @Override
    public List<Game> getGamesByStatus(String status){
        return gameRepository.getGamesByStatus(status);
    }

    @Override
    public List<Game> getGamesByStartDateBefore(Calendar startDate) {
        return gameRepository.getGamesByStartDateBefore(startDate);
    }
}
