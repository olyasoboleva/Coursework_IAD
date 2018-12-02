package service;

import entity.ArenasEntity;
import entity.GamesEntity;

public interface ArenasService {

    /**
     * find arena by id
     * @param arenaId id
     * @return arena
     */
    ArenasEntity getArenaById(int arenaId);

    /**
     * find arena where game takes place
     * @param game game
     * @return arena
     */
    ArenasEntity getArenaByGame(GamesEntity game);

    /**
     * It creates new arena
     * @param arena arena
     * @return new arena if it was saved to database
     */
    ArenasEntity createArena(ArenasEntity arena);

    /**
     * It deletes the arena
     * @param arena arena
     * @return true if arena was deleted
     */
    boolean deleteArena(ArenasEntity arena);

    /**
     * It updates the arena
     * @param arena arena
     * @return new arena if it was correctly updated in database
     */
    ArenasEntity updateArena(ArenasEntity arena);
}
