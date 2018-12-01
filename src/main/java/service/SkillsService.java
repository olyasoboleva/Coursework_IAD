package service;

import entity.SkillsEntity;
import entity.TributesEntity;
import entity.UsersEntity;

import java.util.List;
import java.util.Map;

public interface SkillsService {

    /**
     * It creates new skill
     * @param skill skill
     * @return new skill if it was created
     */
    SkillsEntity createSkill(SkillsEntity skill);

    /**
     * It updates sime skill
     * @param skill skill
     * @return skill if it was correctly updated
     */
    SkillsEntity updateSkill(SkillsEntity skill);

    /**
     * It deletes skill
     * @param skill skill
     * @return true if it was successfully deleted
     */
    boolean deleteSkill(SkillsEntity skill);

    /**
     * Try to find all user skills
     * @param user user
     * @return his skills
     */
    Map<SkillsEntity, Integer> getAllUserSkills(UsersEntity user);

    /**
     * Try to find all tribute skills
     * @param tribute tribute
     * @return his skills
     */
    Map<SkillsEntity, Integer> getAllTributeSkills(TributesEntity tribute);
}
