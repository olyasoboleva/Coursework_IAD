package service;

import entity.UserSkillsEntity;

public interface UserSkillsService {
    UserSkillsEntity createUserSkill(UserSkillsEntity userSkill);
    boolean deleteUserSkill(UserSkillsEntity userSkill);
    UserSkillsEntity updateUserSkills(UserSkillsEntity userSkill);
}
