package service;

import entity.SkillsEntity;
import entity.TributesEntity;
import entity.UsersEntity;

import java.util.List;
import java.util.Map;

public interface SkillsService {

    SkillsEntity createSkill(SkillsEntity skill);
    SkillsEntity updateSkill(SkillsEntity skill);
    boolean deleteSkill(SkillsEntity skill);

    Map<SkillsEntity, Short> getAllUserSkills(UsersEntity user);
    Map<SkillsEntity, Short> getAllTributeSkills(TributesEntity tribute);
}
