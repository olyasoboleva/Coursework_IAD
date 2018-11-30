package repository;

import entity.*;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<UsersEntity, Integer> {
    /**
     * find user by id
     * @param userid id
     * @return user
     */
    UsersEntity findUsersEntityByUserid(int userid);

    /**
     * find users for next game by parameters
     * @param district district
     * @param sex sex
     * @param date1 birthday of user must be after date1
     * @param date2 birthday of user must be before date2
     * @param status status
     * @return list of users
     */
    List<UsersEntity> getUsersEntitiesByDistrictsByDistrictAndSexAndBirthdayGreaterThanAndBirthdayLessThanAndStatus(DistrictsEntity district, boolean sex, Date date1, Date date2, String status);

    /**
     * find user, who play for this tribute
     * @param tribute tribute
     * @return user
     */
    UsersEntity findUsersEntityByTributesByUserid(TributesEntity tribute);

    /**
     * find user by login
     * @param userlogin user login
     * @return user
     */
    UsersEntity findUsersEntityByUserlogin(UserloginEntity userlogin);

    /**
     * find user by status
     * @param status status
     * @return list of users
     */
    List<UsersEntity> getUsersEntitiesByStatus(String status);

    /**
     * find trainer
     * @param training training
     * @return trainer
     */
    UsersEntity findUsersEntityByTrainingsByUserid(TrainingsEntity training);
}
