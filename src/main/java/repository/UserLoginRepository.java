package repository;

import entity.UserLogin;
import entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserLoginRepository extends CrudRepository<UserLogin, Integer> {
    /**
     * find login parameters of user
     * @param user user
     * @return user login
     */
    UserLogin findUserLoginByUser(User user);

    /**
     * find user login by nick
     * @param nick username
     * @return user login entity
     */
    UserLogin findUserLoginByNick(String nick);

    /**
     * find user by nick and password
     *
     * @param nick nick
     * @param password password
     * @return user login
     */
    UserLogin findUserLoginByNickAndPassword(String nick, String password);
}
