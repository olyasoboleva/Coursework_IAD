package repository;


import entity.WeaponsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeaponsRepository extends CrudRepository<WeaponsEntity, Integer> {
    WeaponsEntity findWeaponsEntityByWeaponid(int id);


    /**
     * Позволяет получить оружие, которое связано с передаваемым скиллом
     * @param weaponID оружие
     * @return скиллы все
     */
    @Query(value = "select weapon from WeaponsEntity weapon join SkillsEntity skill  on(weapon.weaponid = skill.weaponid) where skill.weaponid = :weaponID")
    List<WeaponsEntity> getWeaponBySkill(@Param("weaponID")int weaponID);
}
