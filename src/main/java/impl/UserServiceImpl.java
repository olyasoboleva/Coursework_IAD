package impl;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;
import service.StatusService;
import service.UserService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StatusService statusService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, StatusService statusService) {
        this.userRepository = userRepository;
        this.statusService = statusService;
    }

    @Transactional
    @Override
    public User getUserByNick(String nick) {
        return userRepository.findUserByNick(nick);
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
    public List<User> getUsersForGame(District district, boolean sex, Calendar date1, Calendar date2, Status status) {
        return userRepository.getUsersByDistrictAndSexAndBirthdayGreaterThanAndBirthdayLessThanAndStatus(district, sex, date1, date2,status);
    }

    @Override
    public User getUserByTribute(Tribute tribute) {
        return userRepository.findUserByTributesByUser(tribute);
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

    @Override
    public User updateUserLastActivity(User user){
        /*java.sql.Date curDate = new java.sql.Date(System.currentTimeMillis());
        if (user.getLastActivity().getDate()==curDate.getDate() || user.getLastActivity().getYear()==curDate.getYear() || user.getLastActivity().getMonth()==curDate.getMonth()) {
            user.setLastActivity(new java.sql.Date(System.currentTimeMillis()));
            user.setCash(user.getCash()+1000);
            //user.setCash(user.getCash()+statusService.getStatuseByName("Daily prize").getPrice().getCost());
            updateUser(user);
        }*/
        return user;
    }
}
