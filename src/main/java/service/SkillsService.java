package service;

import entity.SkillsEntity;
import entity.UsersEntity;

import java.util.List;

public interface SkillsService {

    SkillsEntity createSkill(SkillsEntity skill);
    SkillsEntity updateSkill(SkillsEntity skill);
    boolean deleteSkill(SkillsEntity skill);

    public List<SkillsEntity> getAllUserSkills(int userID);
}
