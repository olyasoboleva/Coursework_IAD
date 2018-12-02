package service;

import entity.UserLoginEntity;
import entity.UsersEntity;

public interface UserLoginService {
    /**
     * It creates login for user
     * @param login login
     * @return bew login if it was created
     */
    UserLoginEntity createLogin(UserLoginEntity login);

    /**
     * It updates the login for user
     * @param login login
     * @return login if it was correctly updated
     */
    UserLoginEntity updateLogin(UserLoginEntity login);

    /**
     * It deletes login
     * @param login login
     * @return true if it was successfully deleted
     */
    boolean deleteSLogin(UserLoginEntity login);

    /**
     * find login parameters of user
     * @param user user
     * @return user login
     */
    UserLoginEntity getUserLoginByUser(UsersEntity user);

    /**
     * find user login by nick
     * @param nick username
     * @return user login entity
     */
    UserLoginEntity getUserLoginByNick(String nick);

    /**
     * find user by nick and password
     *
     * @param nick nick
     * @param password password
     * @return user login
     */
    UserLoginEntity getUserLoginByNickAndPassword(String nick, String password);
}
