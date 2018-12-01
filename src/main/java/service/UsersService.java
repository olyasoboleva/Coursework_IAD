package service;

import entity.UsersEntity;

public interface UsersService {
    /**
     * Tries to find user by nick
     * @param nick nick
     * @return user if it exists
     */
    UsersEntity findUserByNick(String nick);

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

}
