package service;

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
}
