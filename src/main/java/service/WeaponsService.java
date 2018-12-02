package service;

import entity.SkillsEntity;
import entity.TributesEntity;
import entity.WeaponsEntity;

import java.util.List;

public interface WeaponsService {
    /**
     * It creates new weapon
     * @param weapon weapon
     * @return new weapon if it was created
     */
    WeaponsEntity createWeapon(WeaponsEntity weapon);

    /**
     * It deleted weapon
     * @param weapon weapon
     * @return true if it was successfully deleted
     */
    boolean deleteWeapon(WeaponsEntity weapon);

    /**
     * It updates the weapon
     * @param weapon weapon
     * @return weapon if it was correctly updated
     */
    WeaponsEntity updateWeapon(WeaponsEntity weapon);

    /**
     * It get tributes weapon
     * @param tribute tribute
     * @return his weapon
     */
    List<WeaponsEntity> getTributeWeapons(TributesEntity tribute);

    /**
     * find weapon by id
     * @param id id
     * @return weapon
     */
    WeaponsEntity getWeaponById(int id);

    /**
     * find weapon for this skill
     * @param skill skill
     * @return weapon
     */
    WeaponsEntity getWeaponBySkill(SkillsEntity skill);

    /**
     * find weapons which tribute have
     * @param tribute tribute
     * @return lit of weapons
     */
    List<WeaponsEntity> getWeaponsByOwners(TributesEntity tribute);
}
