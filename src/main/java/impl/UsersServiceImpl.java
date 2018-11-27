package impl;

import entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;
import service.UsersService;

@Service("userService")
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UsersEntity createUserSkill(UsersEntity user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    @Override
    public boolean deleteUserSkill(UsersEntity user) {
        userRepository.delete(user);
        return true;
    }

    @Transactional
    @Override
    public UsersEntity updateUserSkills(UsersEntity user) {
        userRepository.save(user);
        return user;
    }
}
