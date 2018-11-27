package service;

import entity.UserskillsEntity;

public interface UserskillsService {
    UserskillsEntity createUserSkill(UserskillsEntity userSkill);
    boolean deleteUserSkill(UserskillsEntity userSkill);
    UserskillsEntity updateUserSkills(UserskillsEntity userSkill);
}
