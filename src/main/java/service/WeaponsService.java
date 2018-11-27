package service;

import entity.TributesEntity;
import entity.WeaponsEntity;

import java.util.List;

public interface WeaponsService {
    WeaponsEntity createWeapon(WeaponsEntity weapon);
    boolean deleteWeapon(WeaponsEntity weapon);
    WeaponsEntity updateWeapon(WeaponsEntity weapon);

    List<WeaponsEntity> getTributeWeapons(TributesEntity tribute);
}
