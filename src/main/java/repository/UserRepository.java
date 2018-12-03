package repository;

import entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * find user by id
     * @param userId id
     * @return user
     */
    User findUsersEntityByUserId(int userId);

    /**
     * find users for next game by parameters
     * @param district district
     * @param sex sex
     * @param date1 birthday of user must be after date1
     * @param date2 birthday of user must be before date2
     * @param status status
     * @return list of users
     */
    List<User> getUsersByDistrictAndSexAndBirthdayGreaterThanAndBirthdayLessThanAndStatus(District district, boolean sex, Date date1, Date date2, Status status);

    /**
     * find user, who play for this tribute
     * @param tribute tribute
     * @return user
     */
    User findUserByTributesByUser(Tribute tribute);

    /**
     * find user by login
     * @param userLogin user login
     * @return user
     */
    User findUserByUserLogin(UserLogin userLogin);

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
    User findUserByTrainings(Training training);

    /**
     * find all senders of presents to this tribute
     * @param tribute tribute
     * @return list of users
     */
    List<User> findUsersByRecipients(Tribute tribute);


}
