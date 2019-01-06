package service;

import entity.Game;
import entity.PresentsToTribute;
import entity.Tribute;
import entity.WeaponsInGame;

public interface GameProcessService {

    /**
     * It adds health to tribute after using present
     * @param present present
     */
    public void updateHealthByPresent(PresentsToTribute present);

    /**
     * After end of the game it changes statuses to Observer for all users who played in this game
     * @param game game
     */
    public void changeStatusAfterEndOfTheGame(Game game);

    /**
     * To beat someone
     * @param tributeWeapon this weapon will be used
     * @param tributeToBeat he will be beaten
     */
    public void beat(WeaponsInGame tributeWeapon, Tribute tributeToBeat);
}
