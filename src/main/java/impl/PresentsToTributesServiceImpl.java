package impl;

import entity.PresentsToTributesEntity;
import entity.ShopEntity;
import entity.TributesEntity;
import entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PresentsToTributesRepository;
import repository.TributesRepository;
import repository.UserLoginRepository;
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
    public PresentsToTributesEntity getPresentsToTributeById(int sendingId) {
        return presentsToTributesRepository.findPresentsToTributesEntityBySendingId(sendingId);
    }

    @Override
    public List<PresentsToTributesEntity> getPresentsToTributeBySenderAndTribute(UsersEntity sender, TributesEntity tribute) {
        return presentsToTributesRepository.getPresentsToTributesEntityBySenderAndTribute(sender, tribute);
    }

    @Override
    public List<PresentsToTributesEntity> getPresentsToTributeBySender(UsersEntity sender) {
        return presentsToTributesRepository.getPresentsToTributesEntityBySender(sender);
    }

    @Override
    public List<PresentsToTributesEntity> getPresentsToTributeByTribute(TributesEntity tribute) {
        return presentsToTributesRepository.getPresentsToTributesEntityByTribute(tribute);
    }

    @Transactional
    @Override
    public PresentsToTributesEntity createPresentsToTributes(PresentsToTributesEntity present) {
        UsersEntity user = present.getSender();
        ShopEntity product = present.getProduct();
        if (user.getCash() - product.getCost() < 0) {
            return null;
        }
        user.setCash(user.getCash() - product.getCost());
        userRepository.save(user);
        presentsToTributesRepository.save(present);
        return present;
    }

    @Override
    public PresentsToTributesEntity updatePresentsToTributes(PresentsToTributesEntity present) {
        presentsToTributesRepository.save(present);
        return present;
    }

    @Override
    public boolean deletePresentsToTributes(PresentsToTributesEntity present) {
        presentsToTributesRepository.delete(present);
        return true;
    }
}
