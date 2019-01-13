package impl;

import entity.PresentsToTribute;
import entity.Shop;
import entity.Tribute;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PresentsToTributeRepository;
import repository.UserRepository;
import service.PresentsToTributeService;

import java.util.List;

@Service("presentsToTributeService")
public class PresentsToTributeServiceImpl implements PresentsToTributeService {

    private final PresentsToTributeRepository presentsToTributeRepository;
    private final UserRepository userRepository;


    @Autowired
    public PresentsToTributeServiceImpl(PresentsToTributeRepository presentsToTributeRepository, UserRepository userRepository) {
        this.presentsToTributeRepository = presentsToTributeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PresentsToTribute getPresentsToTributeById(int sendingId) {
        return presentsToTributeRepository.findPresentsToTributeBySendingId(sendingId);
    }

    @Override
    public List<PresentsToTribute> getPresentsToTributeBySenderAndTribute(User sender, Tribute tribute) {
        return presentsToTributeRepository.getPresentsToTributesBySenderAndTribute(sender, tribute);
    }

    @Override
    public List<PresentsToTribute> getPresentsToTributeBySender(User sender) {
        return presentsToTributeRepository.getPresentsToTributesBySender(sender);
    }

    @Override
    public List<PresentsToTribute> getPresentsToTributeByTribute(Tribute tribute) {
        return presentsToTributeRepository.getPresentsToTributesByTribute(tribute);
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
        return presentsToTributeRepository.save(present);
    }

    @Override
    public PresentsToTribute updatePresentsToTributes(PresentsToTribute present) {
        return presentsToTributeRepository.save(present);
    }

    @Override
    public boolean deletePresentsToTributes(PresentsToTribute present) {
        presentsToTributeRepository.delete(present);
        return true;
    }

    @Override
    public PresentsToTribute getPresentByProductAndTribute(Shop product, Tribute tribute) {
        return presentsToTributeRepository.getPresentsToTributeByProductAndTribute(product, tribute);
    }
}
