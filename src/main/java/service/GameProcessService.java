package service;

import entity.*;

public interface GameProcessService {

    /**
     * After end of the game it changes statuses to Observer for all users who played in this game
     * @param game game
     */
    public void changeStatusAfterEndOfTheGame(Game game, Tribute winner);

    /**
     * Process of battle between two tributes
     * @param attacking attacking tribute who start battle (has advantage)
     * @param defending defending tribute
     */
    public void fight(Tribute attacking, Tribute defending, String attWeaponName, String defWeaponName);
}
