package repository;

import entity.ArenasEntity;
import entity.GamesEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArenasRepository extends CrudRepository<ArenasEntity, Integer> {
    /**
     * find arena by id
     * @param arenaid id
     * @return arena
     */
    ArenasEntity findArenasEntityByArenaid(int arenaid);

    /**
     * find arena, where game takes place
     * @param game game
     * @return arena
     */
    ArenasEntity findArenasEntityByGamesByArenaid(GamesEntity game);
}
