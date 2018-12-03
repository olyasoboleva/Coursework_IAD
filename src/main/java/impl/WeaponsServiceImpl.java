package impl;

import entity.Skill;
import entity.Tribute;
import entity.Weapon;
import entity.WeaponsInGame;
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

    //FIXME не тестила
    @Transactional
    @Override
    public List<Weapon> getTributeWeapons(Tribute tribute) {
        List<Weapon> tributeWeapons = new ArrayList<>();
        List<WeaponsInGame> gameWeapon = weaponsInGameRepository.getWeaponsInGamesByTribute(tribute);
        List<Weapon> allWeapons = (List<Weapon>) weaponsRepository.findAll();
        for (Weapon weapons : allWeapons) {
            //TODO: сломалось
            /*for (WeaponsInGame weapon : gameWeapon) {
                if (weapons.getWeaponId() == weapon.getWeaponId()) {
                    tributeWeapons.add(weapons);
                }
            }*/
        }
        return tributeWeapons;
    }

    @Transactional
    @Override
    public Weapon createWeapon(Weapon weapon) {
        weaponsRepository.save(weapon);
        return weapon;
    }

    @Transactional
    @Override
    public boolean deleteWeapon(Weapon weapon) {
        weaponsRepository.delete(weapon);
        return true;
    }

    @Transactional
    @Override
    public Weapon updateWeapon(Weapon weapon) {
        weaponsRepository.save(weapon);
        return weapon;
    }

    @Override
    public Weapon getWeaponById(int id) {
        return weaponsRepository.findWeaponsEntityByWeaponId(id);
    }

    @Override
    public Weapon getWeaponBySkill(Skill skill) {
        return weaponsRepository.getWeaponBySkill(skill);
    }

    @Override
    public List<Weapon> getWeaponsByOwners(Tribute tribute) {
        return weaponsRepository.getWeaponsByOwners(tribute);
    }

}
