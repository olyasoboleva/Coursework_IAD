package repository;

import entity.TributesEntity;
import entity.WeaponsEntity;
import entity.WeaponsingameEntity;
import entity.WeaponsingameEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeaponsingameRepository extends CrudRepository<WeaponsingameEntity, WeaponsingameEntityPK> {
}
