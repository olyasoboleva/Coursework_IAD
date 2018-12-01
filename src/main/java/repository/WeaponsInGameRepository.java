package repository;

import entity.TributesEntity;
import entity.WeaponsInGameEntity;
import entity.WeaponsInGameEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeaponsInGameRepository extends CrudRepository<WeaponsInGameEntity, WeaponsInGameEntityPK> {

    /**
     * Try to find all relations between tributes and theirs weapons
     * @param tribute tribute
     * @return links to tribute and his weapons
     */
    List<WeaponsInGameEntity> getWeaponsInGameEntitiesByTribute(TributesEntity tribute);
}
