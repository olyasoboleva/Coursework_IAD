package service;

import entity.WeaponsEntity;

public interface WeaponsService {
    WeaponsEntity createWeapon(WeaponsEntity weapon);
    boolean deleteWeapon(WeaponsEntity weapon);
    WeaponsEntity updateWeapon(WeaponsEntity weapon);
}
