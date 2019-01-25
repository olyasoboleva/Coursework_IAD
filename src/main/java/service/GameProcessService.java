package service;

import entity.*;

import java.util.List;

public interface GameProcessService {

    /**
     * After end of the game it changes statuses to Observer for all users who played in this game
     * @param game game
     * @param winner winner of the game
     */
    public void changeStatusAfterEndOfTheGame(Game game, Tribute winner);

    /**
     * Process of battle between two tributes
     * @param attacking attacking tribute who start battle (has advantage)
     * @param defending defending tribute
     * @param weapon attacking tribute's weapon
     */
    public void fight(Tribute attacking, Tribute defending, Weapon weapon);

    public List<User> selection(List<User> onlineUser, Game game);

    public boolean isGameOver(Game game);
}
