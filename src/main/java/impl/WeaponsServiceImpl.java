package impl;

import entity.TributesEntity;
import entity.WeaponsEntity;
import entity.WeaponsingameEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WeaponsRepository;
import repository.WeaponsingameRepository;
import service.WeaponsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service("weaponsService")
public class WeaponsServiceImpl implements WeaponsService {

    private final WeaponsRepository weaponsRepository;
    private final WeaponsingameRepository weaponsingameRepository;

    @Autowired
    public WeaponsServiceImpl(WeaponsRepository weaponsRepository, WeaponsingameRepository weaponsingameRepository) {
        this.weaponsRepository = weaponsRepository;
        this.weaponsingameRepository = weaponsingameRepository;
    }

    /**
     * Получение оружия трибута
     * @param tribute трибут
     * @return его оружие
     */
    @Transactional
    @Override
    public List<WeaponsEntity> getTributeWeapons(TributesEntity tribute) {
        List<WeaponsEntity> tributeWeapons = new ArrayList<>();
        List<WeaponsingameEntity> gameWeapon = weaponsingameRepository.getWeaponsingameEntitiesByTributesByTributeid(tribute);
        List<WeaponsEntity> allWeapons = weaponsRepository.findAll();
        for (WeaponsEntity weapons : allWeapons) {
            for (WeaponsingameEntity weapon : gameWeapon) {
                //TODO: тут тоже сломалось
                /*
                if (weapons.getWeaponid() == weapon.getWeaponid()) {
                    tributeWeapons.add(weapons);
                }
                */
            }
        }
        return tributeWeapons;
    }

    @Transactional
    @Override
    public WeaponsEntity createWeapon(WeaponsEntity weapon) {
        weaponsRepository.save(weapon);
        return weapon;
    }

    @Transactional
    @Override
    public boolean deleteWeapon(WeaponsEntity weapon) {
        weaponsRepository.delete(weapon);
        return true;
    }

    @Transactional
    @Override
    public WeaponsEntity updateWeapon(WeaponsEntity weapon) {
        weaponsRepository.save(weapon);
        return weapon;
    }
}
