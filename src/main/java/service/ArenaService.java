package service;

import entity.Arena;
import entity.Game;

public interface ArenaService {

    /**
     * find arena by id
     * @param arenaId id
     * @return arena
     */
    Arena getArenaById(int arenaId);

    /**
     * find arena where game takes place
     * @param game game
     * @return arena
     */
    Arena getArenaByGame(Game game);

    /**
     * It creates new arena
     * @param arena arena
     * @return new arena if it was saved to database
     */
    Arena createArena(Arena arena);

    /**
     * It deletes the arena
     * @param arena arena
     * @return true if arena was deleted
     */
    boolean deleteArena(Arena arena);

    /**
     * It updates the arena
     * @param arena arena
     * @return new arena if it was correctly updated in database
     */
    Arena updateArena(Arena arena);
}
