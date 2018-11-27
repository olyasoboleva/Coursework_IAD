package service;

import entity.UserloginEntity;

public interface UserloginService {
    UserloginEntity createLogin(UserloginEntity login);
    UserloginEntity updateLogin(UserloginEntity login);
    boolean deleteSLogin(UserloginEntity login);
}
