package impl;

import entity.UserLoginEntity;
import entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserLoginRepository;
import repository.UserRepository;
import service.UsersService;

@Service("userService")
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final UserLoginRepository userLoginRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, UserLoginRepository userLoginRepository) {
        this.userRepository = userRepository;
        this.userLoginRepository = userLoginRepository;
    }

    @Transactional
    @Override
    public UsersEntity findUserByNick(String nick) {
        UserLoginEntity login = userLoginRepository.findUserLoginEntityByNick(nick);
        return userRepository.findUsersEntityByUserLogin(login);
    }

    @Transactional
    @Override
    public UsersEntity createUser(UsersEntity user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    @Override
    public boolean deleteUser(UsersEntity user) {
        userRepository.delete(user);
        return true;
    }

    @Transactional
    @Override
    public UsersEntity updateUser(UsersEntity user) {
        userRepository.save(user);
        return user;
    }
}
