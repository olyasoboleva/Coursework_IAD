package service;

import entity.*;

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
     * Process of battle between two tributes
     * @param attacking attacking tribute who start battle (has advantage)
     * @param defending defending tribute
     */
    public void fight(Tribute attacking, Tribute defending, String attWeaponName, String defWeaponName);
}
