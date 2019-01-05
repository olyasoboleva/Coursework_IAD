package service;

import entity.*;

import java.util.Date;
import java.util.List;

public interface UserService {


    /**
     * It creates new user
     * @param user user
     * @return new user if he was created
     */
    User createUser(User user);

    /**
     * It deletes user
     * @param user user
     * @return true if it was deleted
     */
    boolean deleteUser(User user);

    /**
     * It updtaes user
     * @param user user
     * @return user if he was updated correctly
     */
    User updateUser(User user);

    /**
     * find user by id
     * @param userId id
     * @return user
     */
    User getUserByUserId(int userId);

    /**
     * Tries to find user by nick
     * @param nick nick
     * @return user if it exists
     */
    User getUserByNick(String nick);

    /**
     * find users for next game by parameters
     * @param district district
     * @param sex sex
     * @param date1 birthday of user must be after date1
     * @param date2 birthday of user must be before date2
     * @param status status
     * @return list of users
     */
    List<User> getUsersForGame(District district, boolean sex, Date date1, Date date2, Status status);

    /**
     * find user, who play for this tribute
     * @param tribute tribute
     * @return user
     */
    User getUserByTribute(Tribute tribute);

    /**
     * find user by login
     * @param userLogin user login
     * @return user
     */
    User getUserByUserLogin(UserLogin userLogin);

    /**
     * find user by status
     * @param status status
     * @return list of users
     */
    List<User> getUsersByStatus(Status status);

    /**
     * find trainer
     * @param training training
     * @return trainer
     */
    User getTrainerOfTraining(Training training);

    /**
     * find all senders of presents to this tribute
     * @param tribute tribute
     * @return list of users
     */
    List<User> getSendersOfPresentsByTribute(Tribute tribute);

    /**
     * update last activity date for getting daily prize
     * @return is successful
     */
    User updateUserLastActivity(User user);
}
