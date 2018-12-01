package repository;

import entity.WeaponsInGameEntity;
import entity.WeaponsInGameEntityPK;
import org.springframework.data.repository.CrudRepository;

public interface WeaponsInGameRepository extends CrudRepository<WeaponsInGameEntity, WeaponsInGameEntityPK> {
}
