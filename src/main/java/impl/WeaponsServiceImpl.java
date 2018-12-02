package impl;

import entity.SkillsEntity;
import entity.TributesEntity;
import entity.WeaponsEntity;
import entity.WeaponsInGameEntity;
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

    @Transactional
    @Override
    public List<WeaponsEntity> getTributeWeapons(TributesEntity tribute) {
        List<WeaponsEntity> tributeWeapons = new ArrayList<>();
        List<WeaponsInGameEntity> gameWeapon = weaponsInGameRepository.getWeaponsInGameEntitiesByTribute(tribute);
        List<WeaponsEntity> allWeapons = weaponsRepository.findAll();
        for (WeaponsEntity weapons : allWeapons) {
            for (WeaponsInGameEntity weapon : gameWeapon) {
                if (weapons.getWeaponId() == weapon.getWeaponId()) {
                    tributeWeapons.add(weapons);
                }
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

    @Override
    public WeaponsEntity getWeaponById(int id) {
        return weaponsRepository.findWeaponsEntityByWeaponId(id);
    }

    @Override
    public WeaponsEntity getWeaponBySkill(SkillsEntity skill) {
        return weaponsRepository.getWeaponsEntitiesBySkill(skill);
    }

    @Override
    public List<WeaponsEntity> getWeaponsByOwners(TributesEntity tribute) {
        return weaponsRepository.getWeaponsEntitiesByOwners(tribute);
    }

}
