package repository;

import entity.WeaponsEntity;
import entity.WeaponsingameEntity;
import entity.WeaponsingameEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeaponsingameRepository extends CrudRepository<WeaponsingameEntity, WeaponsingameEntityPK> {
    WeaponsEntity findWeaponsingameEntityByTributeidAndAndWeaponid(int tributeId, int weaponId);
    List<WeaponsEntity> getWeaponsingameEntitiesByTributeid(int tributeId);
}
