package repository;

import entity.UserLoginEntity;
import entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserLoginRepository extends CrudRepository<UserLoginEntity, Integer> {
    /**
     * find login parameters of user
     * @param user user
     * @return user login
     */
    UserLoginEntity findUserLoginEntityByUser(UsersEntity user);

    /**
     * find user login by nick
     * @param nick username
     * @return user login entity
     */
    UserLoginEntity findUserLoginEntityByNick(String nick);

    /**
     * find user by nick and password
     *
     * @param nick nick
     * @param password password
     * @return user login
     */
    UserLoginEntity findUserLoginEntityByNickAndPassword(String nick, String password);
}
