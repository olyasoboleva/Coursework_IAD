package impl;

import entity.TributesEntity;
import entity.WeaponsEntity;
import entity.WeaponsInGameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WeaponsInGameRepository;
import repository.WeaponsRepository;
import service.WeaponsInGameService;

import java.util.List;

@Service("weaponsInGameService")
public class WeaponsInGameServiceImpl implements WeaponsInGameService {

    private final WeaponsInGameRepository weaponsInGameRepository;
    private final WeaponsRepository weaponsRepository;

    @Autowired
    public WeaponsInGameServiceImpl(WeaponsInGameRepository weaponsInGameRepository, WeaponsRepository weaponsRepository) {
        this.weaponsInGameRepository = weaponsInGameRepository;

        this.weaponsRepository = weaponsRepository;
    }

    /**
     * It adds new weapon to tribute after checking its quantity
     * @param weaponInGame relation between tribute and weapon
     * @return adds relation if quantity is lower than 3 and null if not
     */
    @Transactional
    @Override
    public WeaponsInGameEntity createWeaponsInGame(WeaponsInGameEntity weaponInGame) {
        TributesEntity tribute = weaponInGame.getTribute();
        List<WeaponsEntity> list = weaponsRepository.getWeaponsEntitiesByOwners(tribute);
        if (list.size() < 3 ) {
            weaponsInGameRepository.save(weaponInGame);
            return weaponInGame;
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public boolean deleteWeaponsInGame(WeaponsInGameEntity weaponInGame) {
        weaponsInGameRepository.delete(weaponInGame);
        return true;
    }

}
