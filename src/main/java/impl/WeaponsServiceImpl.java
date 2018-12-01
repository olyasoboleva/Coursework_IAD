package impl;

import entity.TributesEntity;
import entity.WeaponsEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WeaponsRepository;
import repository.WeaponsInGameRepository;
import service.WeaponsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service("weaponsService")
public class WeaponsServiceImpl implements WeaponsService {

    private final WeaponsRepository weaponsRepository;
    private final WeaponsInGameRepository weaponsInGameRepository;

    @Autowired
    public WeaponsServiceImpl(WeaponsRepository weaponsRepository, WeaponsInGameRepository weaponsInGameRepository) {
        this.weaponsRepository = weaponsRepository;
        this.weaponsInGameRepository = weaponsInGameRepository;
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
        //FIXME: сломалось
        /*List<WeaponsInGameEntity> gameWeapon = weaponsInGameRepository.getWeaponsingameEntitiesByTributesByTributeid(tribute);
        List<WeaponsEntity> allWeapons = weaponsRepository.findAll();
        for (WeaponsEntity weapons : allWeapons) {
            for (WeaponsInGameEntity weapon : gameWeapon) {
                if (weapons.getWeaponId() == weapon.getWeaponId()) {
                    tributeWeapons.add(weapons);
                }
            }
        }*/
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
