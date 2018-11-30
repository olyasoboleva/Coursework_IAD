package repository;


import entity.SkillsEntity;
import entity.TributesEntity;
import entity.WeaponsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeaponsRepository extends JpaRepository<WeaponsEntity, Integer> {
    WeaponsEntity findWeaponsEntityByWeaponid(int id);

    /**
     * find weapon for this skill
     * @param skill skill
     * @return weapon
     */
    WeaponsEntity getWeaponsEntitiesBySkillByWeaponid(SkillsEntity skill);

    /**
     * find weapons, which tribute have
     * @param tribute tribute
     * @return lit of weapons
     */
    List<WeaponsEntity> getWeaponsEntitiesByOwners(TributesEntity tribute);
}
