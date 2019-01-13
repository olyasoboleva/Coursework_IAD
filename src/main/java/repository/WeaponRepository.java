package repository;


import entity.Skill;
import entity.Tribute;
import entity.Weapon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeaponRepository extends CrudRepository<Weapon, Integer> {

    /**
     * find weapon by id
     * @param id id
     * @return weapon
     */
    Weapon findWeaponByWeaponId(int id);

    /**
     * find weapon for this skill
     * @param skill skill
     * @return weapon
     */
    Weapon getWeaponBySkill(Skill skill);

    /**
     * find weapons which tribute have
     * @param tribute tribute
     * @return lit of weapons
     */
    List<Weapon> getWeaponByOwners(Tribute tribute);

    /**
     * find weapon by name
     * @param name name
     * @return weapon
     */
    Weapon findWeaponByName(String name);
}
