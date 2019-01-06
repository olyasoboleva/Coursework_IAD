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
    @Autowired
    public GameProcessServiceImpl(TributeRepository tributeRepository, UserRepository userRepository, StatusRepository statusRepository) {
        this.tributeRepository = tributeRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
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


    public void beat(WeaponsInGame tributeWeapon, Tribute tributeToBeat) {
        Weapon weapon = tributeWeapon.getWeapon();
        //FIXME: урон нормально надо прописать)))
        if (tributeToBeat.getHealth() - weapon.getDamage() <=0) {
            tributeToBeat.setHealth(0);
            tributeToBeat.setStatus("Погибший трибут");
        } else {
            tributeToBeat.setHealth(tributeToBeat.getHealth() - weapon.getDamage());
        }
        tributeRepository.save(tributeToBeat);
    }

}
