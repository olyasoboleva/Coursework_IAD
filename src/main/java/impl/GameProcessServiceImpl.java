package impl;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.*;
import service.GameProcessService;

import java.util.List;

@Service("gameProcess")
public class GameProcessServiceImpl implements GameProcessService {
    private final TributeRepository tributeRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final WeaponRepository weaponRepository;

    @Autowired
    public GameProcessServiceImpl(TributeRepository tributeRepository, UserRepository userRepository, StatusRepository statusRepository, WeaponRepository weaponRepository) {
        this.tributeRepository = tributeRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.weaponRepository = weaponRepository;
    }

    //TODO: кажется, вычитание из количества подарка мы тут не пропишем, потому что решили не менять это в БД
    @Transactional
    public void updateHealthByPresent(PresentsToTribute present) {
        Tribute tribute = present.getTribute();
        Shop product = present.getProduct();
        if (tribute.getHealth() + product.getHealthRecovery() > 100) {
            tribute.setHealth(100);
        } else {
            tribute.setHealth(tribute.getHealth() + product.getHealthRecovery());
        }
        tributeRepository.save(tribute);
    }


    public void changeStatusAfterEndOfTheGame(Game game) {
        List<Tribute> allTributes = tributeRepository.getTributesByGame(game);
        Status status = statusRepository.findStatuseByName("Наблюдатель");
        for (Tribute tribute: allTributes) {
            User user = userRepository.findUserByTributesByUser(tribute);
            user.setStatus(status);
            userRepository.save(user);
        }
    }


    @Override
    public void fight(Tribute attacking, Tribute defending, String attWeaponName, String defWeaponName) {
        Weapon attWeapon = weaponRepository.findWeaponByName(attWeaponName);
        Weapon defWeapon = weaponRepository.findWeaponByName(defWeaponName);
        for (int i=0; i<3 && attacking.getHealth()>=0 && defending.getHealth()>=0; i++){
            //if ()
        }

    }

}
