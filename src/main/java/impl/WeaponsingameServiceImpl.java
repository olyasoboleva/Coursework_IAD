package impl;

import entity.WeaponsingameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WeaponsingameRepository;
import service.WeaponsingameService;

import java.util.List;

@Service("weaponsInGameService")
public class WeaponsingameServiceImpl implements WeaponsingameService {

    private final WeaponsingameRepository weaponsInGameRepository;

    @Autowired
    public WeaponsingameServiceImpl(WeaponsingameRepository weaponsInGameRepository) {
        this.weaponsInGameRepository = weaponsInGameRepository;
    }

    @Transactional
    @Override
    public WeaponsingameEntity createWeaponsInGame(WeaponsingameEntity weaponInGame) {
        weaponsInGameRepository.save(weaponInGame);
        return weaponInGame;
    }

    @Transactional
    @Override
    public boolean deleteWeaponsInGame(WeaponsingameEntity weaponInGame) {
        weaponsInGameRepository.delete(weaponInGame);
        return true;
    }

}
