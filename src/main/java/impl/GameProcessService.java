package impl;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.*;

import java.util.List;

@Service("gameProcess")
public class GameProcessService {
//FIXME: сори, мне пока лень делать для этого интерфейс. вдруг мы всё это уберём
    //Так-то всё равно нам когда-нибудь всё это понадобится. Но, может, надо будет убрать тут взаимодействие с БД будет и перенести из сервисов
    private final TributeRepository tributeRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    @Autowired
    public GameProcessService(TributeRepository tributeRepository, UserRepository userRepository, StatusRepository statusRepository) {
        this.tributeRepository = tributeRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    /**
     * It adds health to tribute after using present
     * @param present present
     */
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

    /**
     * After end of the game it changes statuses to Observer for all users who played in this game
     * @param game game
     */
    public void changeStatusAfterEndOfTheGame(Game game) {
        List<Tribute> allTributes = tributeRepository.getTributesByGame(game);
        Status status = statusRepository.findStatuseByName("Наблюдатель");
        for (Tribute tribute: allTributes) {
            User user = userRepository.findUserByTributesByUser(tribute);
            user.setStatus(status);
            userRepository.save(user);
        }
    }

    /**
     * To beat someone
     * @param tributeWeapon this weapon will be used
     * @param tributeToBeat he will be beaten
     */
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
