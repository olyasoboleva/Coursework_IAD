package repository;

import entity.*;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<UsersEntity, Integer> {
    /**
     * find user by id
     * @param userId id
     * @return user
     */
    UsersEntity findUsersEntityByUserId(int userId);

    /**
     * find users for next game by parameters
     * @param district district
     * @param sex sex
     * @param date1 birthday of user must be after date1
     * @param date2 birthday of user must be before date2
     * @param status status
     * @return list of users
     */
    List<UsersEntity> getUsersEntitiesByDistrictAndSexAndBirthdayGreaterThanAndBirthdayLessThanAndStatus(DistrictsEntity district, boolean sex, Date date1, Date date2, StatusesEntity status);

    /**
     * find user, who play for this tribute
     * @param tribute tribute
     * @return user
     */
    UsersEntity findUsersEntityByTributesByUser(TributesEntity tribute);

    /**
     * find user by login
     * @param userLogin user login
     * @return user
     */
    UsersEntity findUsersEntityByUserLogin(UserLoginEntity userLogin);

    /**
     * find user by status
     * @param status status
     * @return list of users
     */
    List<UsersEntity> getUsersEntitiesByStatus(StatusesEntity status);

    /**
     * find trainer
     * @param training training
     * @return trainer
     */
    UsersEntity findUsersEntityByTrainings(TrainingsEntity training);

    /**
     * find all senders of presents to this tribute
     * @param tribute tribute
     * @return list of users
     */
    List<UsersEntity> findUsersEntitiesByRecipients(TributesEntity tribute);
}
