package repository;


import entity.SkillsEntity;
import entity.WeaponsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeaponsRepository extends JpaRepository<WeaponsEntity, Integer> {
    WeaponsEntity findWeaponsEntityByWeaponid(int id);

    /**
     * Позволяет получить оружие, которое связано с передаваемым скиллом
     * @param skill
     * @return оружие
     */
    List<WeaponsEntity> getWeaponsEntitiesBySkillByWeaponid(SkillsEntity skill);
}
