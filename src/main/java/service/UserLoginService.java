package service;

import entity.UserLoginEntity;

public interface UserLoginService {
    UserLoginEntity createLogin(UserLoginEntity login);
    UserLoginEntity updateLogin(UserLoginEntity login);
    boolean deleteSLogin(UserLoginEntity login);
}
