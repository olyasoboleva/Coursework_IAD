package repository;

import entity.ArenasEntity;
import entity.GamesEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArenasRepository extends CrudRepository<ArenasEntity, Integer> {
    /**
     * find arena by id
     * @param arenaId id
     * @return arena
     */
    ArenasEntity findArenasEntityByArenaId(int arenaId);

    /**
     * find arena, where game takes place
     * @param game game
     * @return arena
     */
    ArenasEntity findArenasEntityByGame(GamesEntity game);
}
