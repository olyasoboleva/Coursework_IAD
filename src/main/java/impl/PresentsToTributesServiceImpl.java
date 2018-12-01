package impl;

import entity.PresentsToTributesEntity;
import entity.ShopEntity;
import entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PresentsToTributesRepository;
import repository.UserLoginRepository;
import repository.UserRepository;
import service.PresentsToTributesService;

@Service("presentsToTributesService")
public class PresentsToTributesServiceImpl implements PresentsToTributesService {

    private final PresentsToTributesRepository presentsToTributesRepository;
    private final UserRepository userRepository;
    private final UserLoginRepository userLoginRepository;
    UsersServiceImpl usersService;

    @Autowired
    public PresentsToTributesServiceImpl(PresentsToTributesRepository presentsToTributesRepository, UserRepository userRepository, UserLoginRepository userLoginRepository) {
        this.presentsToTributesRepository = presentsToTributesRepository;
        this.userRepository = userRepository;
        usersService = new UsersServiceImpl(userRepository, userLoginRepository);
        this.userLoginRepository = userLoginRepository;
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
        user = usersService.updateUser(user);
        presentsToTributesRepository.save(present);
        return present;
    }
}
