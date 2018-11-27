package service;

import entity.GamesEntity;

public interface GamesService {
    GamesEntity createGame(GamesEntity game);
    boolean deleteGame(GamesEntity game);
    GamesEntity updateGame(GamesEntity game);
}
