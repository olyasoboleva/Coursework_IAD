package impl;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserLoginRepository;
import repository.UserRepository;
import service.UsersService;

import java.sql.Date;
import java.util.List;

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
    public User getUserByNick(String nick) {
        UserLogin login = userLoginRepository.findUserLoginByNick(nick);
        return userRepository.findUserByUserLogin(login);
    }

    @Transactional
    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    @Override
    public boolean deleteUser(User user) {
        userRepository.delete(user);
        return true;
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserByUserId(int userId) {
        return userRepository.findUsersEntityByUserId(userId);
    }

    @Override
    public List<User> getUsersForGame(District district, boolean sex, Date date1, Date date2, Status status) {
        return userRepository.getUsersByDistrictAndSexAndBirthdayGreaterThanAndBirthdayLessThanAndStatus(district, sex, date1, date2,status);
    }

    @Override
    public User getUserByTribute(Tribute tribute) {
        return userRepository.findUserByTributesByUser(tribute);
    }

    @Override
    public User getUserByUserLogin(UserLogin userLogin) {
        return userRepository.findUserByUserLogin(userLogin);
    }

    @Override
    public List<User> getUsersByStatus(Status status) {
        return userRepository.getUsersByStatus(status);
    }

    @Override
    public User getTrainerOfTraining(Training training) {
        return userRepository.findUserByTrainings(training);
    }

    @Override
    public List<User> getSendersOfPresentsByTribute(Tribute tribute) {
        return userRepository.findUsersByRecipients(tribute);
    }
}
