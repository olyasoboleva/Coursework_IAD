package service;

import entity.ArenasEntity;

public interface ArenasService {
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
