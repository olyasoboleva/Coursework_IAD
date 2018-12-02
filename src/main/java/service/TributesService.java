package service;

import entity.GamesEntity;
import entity.ShopEntity;
import entity.TributesEntity;
import entity.UsersEntity;

import java.util.List;

public interface TributesService {
    /**
     * It creates new tribute
     * @param tribute tribute
     * @return new tribute if it was saved
     */
    TributesEntity createTribute(TributesEntity tribute);

    /**
     * It deletes tribute
     * @param tribute tribute
     * @return true if it was correctly deleted
     */
    boolean deleteTribute(TributesEntity tribute);

    /**
     * It updates the tribute
     * @param tribute tribute
     * @return tribute if it was updated correctly
     */
    TributesEntity updateTribute(TributesEntity tribute);

    /**
     * find tribute by id
     *
     * @param tributeId id
     * @return tribute
     */
    TributesEntity getTributeById(long tributeId);

    /**
     * find all tributes of user
     *
     * @param user user
     * @return list of tributes
     */
    List<TributesEntity> getTributesByUser(UsersEntity user);

    /**
     * find all tributes of game
     *
     * @param game game
     * @return list of tributes
     */
    List<TributesEntity> getTributesByGame(GamesEntity game);

    /**
     * find all tributes of game with this status
     *
     * @param status status
     * @param game game
     * @return list of games
     */
    List<TributesEntity> getTributesByStatusAndGame(String status, GamesEntity game);

}
