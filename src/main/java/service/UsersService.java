package service;

import entity.UsersEntity;

public interface UsersService {
    UsersEntity createUserSkill(UsersEntity user);
    boolean deleteUserSkill(UsersEntity user);
    UsersEntity updateUserSkills(UsersEntity user);

}
