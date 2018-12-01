package impl;

import entity.WeaponsInGameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WeaponsInGameRepository;
import service.WeaponsInGameService;

@Service("weaponsInGameService")
public class WeaponsInGameServiceImpl implements WeaponsInGameService {

    private final WeaponsInGameRepository weaponsInGameRepository;

    @Autowired
    public WeaponsInGameServiceImpl(WeaponsInGameRepository weaponsInGameRepository) {
        this.weaponsInGameRepository = weaponsInGameRepository;
    }

    @Transactional
    @Override
    public WeaponsInGameEntity createWeaponsInGame(WeaponsInGameEntity weaponInGame) {
        weaponsInGameRepository.save(weaponInGame);
        return weaponInGame;
    }

    @Transactional
    @Override
    public boolean deleteWeaponsInGame(WeaponsInGameEntity weaponInGame) {
        weaponsInGameRepository.delete(weaponInGame);
        return true;
    }

}
