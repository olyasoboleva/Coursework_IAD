package service;

import entity.*;

import java.sql.Date;
import java.util.List;

public interface UsersService {


    /**
     * It creates new user
     * @param user user
     * @return new user if he was created
     */
    UsersEntity createUser(UsersEntity user);

    /**
     * It deletes user
     * @param user user
     * @return true if it was deleted
     */
    boolean deleteUser(UsersEntity user);

    /**
     * It updtaes user
     * @param user user
     * @return user if he was updated correctly
     */
    UsersEntity updateUser(UsersEntity user);

    /**
     * find user by id
     * @param userId id
     * @return user
     */
    UsersEntity getUserByUserId(int userId);

    /**
     * Tries to find user by nick
     * @param nick nick
     * @return user if it exists
     */
    UsersEntity getUserByNick(String nick);

    /**
     * find users for next game by parameters
     * @param district district
     * @param sex sex
     * @param date1 birthday of user must be after date1
     * @param date2 birthday of user must be before date2
     * @param status status
     * @return list of users
     */
    List<UsersEntity> getUsersForGame(DistrictsEntity district, boolean sex, Date date1, Date date2, StatusesEntity status);

    /**
     * find user, who play for this tribute
     * @param tribute tribute
     * @return user
     */
    UsersEntity getUserByTribute(TributesEntity tribute);

    /**
     * find user by login
     * @param userLogin user login
     * @return user
     */
    UsersEntity getUserByUserLogin(UserLoginEntity userLogin);

    /**
     * find user by status
     * @param status status
     * @return list of users
     */
    List<UsersEntity> getUsersByStatus(StatusesEntity status);

    /**
     * find trainer
     * @param training training
     * @return trainer
     */
    UsersEntity getTrainerOfTraining(TrainingsEntity training);

    /**
     * find all senders of presents to this tribute
     * @param tribute tribute
     * @return list of users
     */
    List<UsersEntity> getSendersOfPresentsByTribute(TributesEntity tribute);
}
