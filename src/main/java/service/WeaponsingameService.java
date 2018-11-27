package service;

import entity.WeaponsingameEntity;

public interface WeaponsingameService {
    WeaponsingameEntity createWeaponsInGame(WeaponsingameEntity weaponInGame);
    boolean deleteWeaponsInGame(WeaponsingameEntity weaponInGame);
}
