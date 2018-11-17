package repository;

import entity.WeaponsEntity;
import org.springframework.data.repository.CrudRepository;

public interface WeaponsRepository extends CrudRepository<WeaponsEntity, Integer> {
    WeaponsEntity findWeaponsEntityByWeaponid(int id);
}
