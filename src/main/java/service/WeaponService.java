package service;

import entity.Skill;
import entity.Tribute;
import entity.Weapon;

import java.util.List;

public interface WeaponService {
    /**
     * It creates new weapon
     * @param weapon weapon
     * @return new weapon if it was created
     */
    Weapon createWeapon(Weapon weapon);

    /**
     * It deleted weapon
     * @param weapon weapon
     * @return true if it was successfully deleted
     */
    boolean deleteWeapon(Weapon weapon);

    /**
     * It updates the weapon
     * @param weapon weapon
     * @return weapon if it was correctly updated
     */
    Weapon updateWeapon(Weapon weapon);

    /**
     * It get tributes weapon
     * @param tribute tribute
     * @return his weapon
     */
    List<Weapon> getTributeWeapons(Tribute tribute);

    /**
     * find weapon by id
     * @param id id
     * @return weapon
     */
    Weapon getWeaponById(int id);

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
    List<Weapon> getWeaponsByOwners(Tribute tribute);
}
