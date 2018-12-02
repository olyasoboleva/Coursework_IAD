package impl;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.StatusesRepository;
import repository.TributesRepository;
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
    public UsersEntity getUserByNick(String nick) {
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

    @Override
    public UsersEntity getUserByUserId(int userId) {
        return userRepository.findUsersEntityByUserId(userId);
    }

    @Override
    public List<UsersEntity> getUsersForGame(DistrictsEntity district, boolean sex, Date date1, Date date2, StatusesEntity status) {
        return userRepository.getUsersEntitiesByDistrictAndSexAndBirthdayGreaterThanAndBirthdayLessThanAndStatus(district, sex, date1, date2,status);
    }

    @Override
    public UsersEntity getUserByTribute(TributesEntity tribute) {
        return userRepository.findUsersEntityByTributesByUser(tribute);
    }

    @Override
    public UsersEntity getUserByUserLogin(UserLoginEntity userLogin) {
        return userRepository.findUsersEntityByUserLogin(userLogin);
    }

    @Override
    public List<UsersEntity> getUsersByStatus(StatusesEntity status) {
        return userRepository.getUsersEntitiesByStatus(status);
    }

    @Override
    public UsersEntity getTrainerOfTraining(TrainingsEntity training) {
        return userRepository.findUsersEntityByTrainings(training);
    }

    @Override
    public List<UsersEntity> getSendersOfPresentsByTribute(TributesEntity tribute) {
        return userRepository.findUsersEntitiesByRecipients(tribute);
    }
}
