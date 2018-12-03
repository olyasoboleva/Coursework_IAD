package impl;

import entity.Skill;
import entity.Tribute;
import entity.Weapon;
import entity.WeaponsInGame;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WeaponRepository;
import repository.WeaponsInGameRepository;
import service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service("weaponService")
public class WeaponServiceImpl implements WeaponService {

    private final WeaponRepository weaponRepository;
    private final WeaponsInGameRepository weaponsInGameRepository;

    @Autowired
    public WeaponServiceImpl(WeaponRepository weaponRepository, WeaponsInGameRepository weaponsInGameRepository) {
        this.weaponRepository = weaponRepository;
        this.weaponsInGameRepository = weaponsInGameRepository;
    }

    //FIXME не тестила
    @Transactional
    @Override
    public List<Weapon> getTributeWeapons(Tribute tribute) {
        List<Weapon> tributeWeapons = new ArrayList<>();
        List<WeaponsInGame> gameWeapon = weaponsInGameRepository.getWeaponsInGamesByTribute(tribute);
        List<Weapon> allWeapons = (List<Weapon>) weaponRepository.findAll();
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
        weaponRepository.save(weapon);
        return weapon;
    }

    @Transactional
    @Override
    public boolean deleteWeapon(Weapon weapon) {
        weaponRepository.delete(weapon);
        return true;
    }

    @Transactional
    @Override
    public Weapon updateWeapon(Weapon weapon) {
        weaponRepository.save(weapon);
        return weapon;
    }

    @Override
    public Weapon getWeaponById(int id) {
        return weaponRepository.findWeaponsEntityByWeaponId(id);
    }

    @Override
    public Weapon getWeaponBySkill(Skill skill) {
        return weaponRepository.getWeaponBySkill(skill);
    }

    @Override
    public List<Weapon> getWeaponsByOwners(Tribute tribute) {
        return weaponRepository.getWeaponByOwners(tribute);
    }

}
