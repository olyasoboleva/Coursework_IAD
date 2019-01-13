package repository;

import entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
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
     * find user by nick
     * @param nick username
     * @return user entity
     */
    User findUserByNick(String nick);


    /**
     * find users for next game by parameters
     * @param district district
     * @param sex sex
     * @param date1 birthday of user must be after date1
     * @param date2 birthday of user must be before date2
     * @param status status
     * @return list of users
     */
    List<User> getUsersByDistrictAndSexAndBirthdayGreaterThanAndBirthdayLessThanAndStatus(District district, boolean sex, Calendar date1, Calendar date2, Status status);

    /**
     * find user, who play for this tribute
     * @param tribute tribute
     * @return user
     */
    User findUserByTributesByUser(Tribute tribute);

    /**
     * find user by status
     * @param status status
     * @return list of users
     */
    List<User> getUsersByStatus(Status status);

    /**
     * find all senders of presents to this tribute
     * @param tribute tribute
     * @return list of users
     */
    List<User> findUsersByRecipients(Tribute tribute);

    @Query(value = "select users from User  users LEFT JOIN FETCH Skill skill where users.userId=:id")
    List<User> getUsersWithSkill(@Param("id")int id);
}
