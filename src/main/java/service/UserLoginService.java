package service;

import entity.UserLoginEntity;

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
}
