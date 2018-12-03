package service;

import entity.UserLogin;
import entity.User;

public interface UserLoginService {
    /**
     * It creates login for user
     * @param login login
     * @return bew login if it was created
     */
    UserLogin createLogin(UserLogin login);

    /**
     * It updates the login for user
     * @param login login
     * @return login if it was correctly updated
     */
    UserLogin updateLogin(UserLogin login);

    /**
     * It deletes login
     * @param login login
     * @return true if it was successfully deleted
     */
    boolean deleteSLogin(UserLogin login);

    /**
     * find login parameters of user
     * @param user user
     * @return user login
     */
    UserLogin getUserLoginByUser(User user);

    /**
     * find user login by nick
     * @param nick username
     * @return user login entity
     */
    UserLogin getUserLoginByNick(String nick);

    /**
     * find user by nick and password
     *
     * @param nick nick
     * @param password password
     * @return user login
     */
    UserLogin getUserLoginByNickAndPassword(String nick, String password);
}
