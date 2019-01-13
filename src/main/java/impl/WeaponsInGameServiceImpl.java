package impl;

import entity.Tribute;
import entity.Weapon;
import entity.WeaponsInGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WeaponsInGameRepository;
import repository.WeaponRepository;
import service.WeaponsInGameService;

import java.util.List;

@Service("weaponsInGameService")
public class WeaponsInGameServiceImpl implements WeaponsInGameService {

    private final WeaponsInGameRepository weaponsInGameRepository;
    private final WeaponRepository weaponRepository;

    @Autowired
    public WeaponsInGameServiceImpl(WeaponsInGameRepository weaponsInGameRepository, WeaponRepository weaponRepository) {
        this.weaponsInGameRepository = weaponsInGameRepository;

        this.weaponRepository = weaponRepository;
    }

    /**
     * It adds new weapon to tribute after checking its quantity
     * @param weaponInGame relation between tribute and weapon
     * @return adds relation if quantity is lower than 3 and null if not
     */
    @Transactional
    @Override
    public WeaponsInGame createWeaponsInGame(WeaponsInGame weaponInGame) {
        Tribute tribute = weaponInGame.getTribute();
        List<Weapon> list = weaponRepository.getWeaponByOwners(tribute);
        if (list.size() < 3 ) {
            return weaponsInGameRepository.save(weaponInGame);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public boolean deleteWeaponsInGame(WeaponsInGame weaponInGame) {
        weaponsInGameRepository.delete(weaponInGame);
        return true;
    }

    @Override
    public List<WeaponsInGame> getWeaponsInGameByTribute(Tribute tribute) {
        return weaponsInGameRepository.getWeaponsInGamesByTribute(tribute);
    }
}
