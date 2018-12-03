package impl;

import entity.PresentsToTribute;
import entity.Shop;
import entity.Tribute;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PresentsToTributesRepository;
import repository.UserRepository;
import service.PresentsToTributesService;

import java.util.List;

@Service("presentsToTributesService")
public class PresentsToTributesServiceImpl implements PresentsToTributesService {

    private final PresentsToTributesRepository presentsToTributesRepository;
    private final UserRepository userRepository;


    @Autowired
    public PresentsToTributesServiceImpl(PresentsToTributesRepository presentsToTributesRepository, UserRepository userRepository) {
        this.presentsToTributesRepository = presentsToTributesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PresentsToTribute getPresentsToTributeById(int sendingId) {
        return presentsToTributesRepository.findPresentsToTributeBySendingId(sendingId);
    }

    @Override
    public List<PresentsToTribute> getPresentsToTributeBySenderAndTribute(User sender, Tribute tribute) {
        return presentsToTributesRepository.getPresentsToTributesBySenderAndTribute(sender, tribute);
    }

    @Override
    public List<PresentsToTribute> getPresentsToTributeBySender(User sender) {
        return presentsToTributesRepository.getPresentsToTributesBySender(sender);
    }

    @Override
    public List<PresentsToTribute> getPresentsToTributeByTribute(Tribute tribute) {
        return presentsToTributesRepository.getPresentsToTributesByTribute(tribute);
    }

    @Transactional
    @Override
    public PresentsToTribute createPresentsToTributes(PresentsToTribute present) {
        User user = present.getSender();
        Shop product = present.getProduct();
        if (user.getCash() - product.getCost() < 0) {
            return null;
        }
        user.setCash(user.getCash() - product.getCost());
        userRepository.save(user);
        presentsToTributesRepository.save(present);
        return present;
    }

    @Override
    public PresentsToTribute updatePresentsToTributes(PresentsToTribute present) {
        presentsToTributesRepository.save(present);
        return present;
    }

    @Override
    public boolean deletePresentsToTributes(PresentsToTribute present) {
        presentsToTributesRepository.delete(present);
        return true;
    }
}
