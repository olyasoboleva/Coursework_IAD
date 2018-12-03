package repository;

import entity.Arena;
import entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface ArenasRepository extends CrudRepository<Arena, Integer> {
    /**
     * find arena by id
     * @param arenaId id
     * @return arena
     */
    Arena findArenaByArenaId(int arenaId);


    /**
     * find arena where game takes place
     * @param game game
     * @return arena
     */
    Arena findArenaByGame(Game game);
}
