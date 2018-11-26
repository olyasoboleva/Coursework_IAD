package repository;

import entity.TributesEntity;
import entity.WeaponsEntity;
import entity.WeaponsingameEntity;
import entity.WeaponsingameEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeaponsingameRepository extends CrudRepository<WeaponsingameEntity, WeaponsingameEntityPK> {
    WeaponsingameEntity findWeaponsingameEntityByTributesByTributeidAndWeaponsByWeaponid(TributesEntity tribute, WeaponsEntity weapons);
    List<WeaponsingameEntity> getWeaponsingameEntitiesByTributesByTributeid(TributesEntity tributes);
}
