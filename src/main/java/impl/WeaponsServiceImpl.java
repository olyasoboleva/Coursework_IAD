package impl;

import entity.WeaponsEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WeaponsRepository;
import service.WeaponsService;
import org.springframework.beans.factory.annotation.Autowired;

@Service("weaponsService")
public class WeaponsServiceImpl implements WeaponsService {

    private final WeaponsRepository weaponsRepository;

    @Autowired
    public WeaponsServiceImpl(WeaponsRepository weaponsRepository) {
        this.weaponsRepository = weaponsRepository;
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
