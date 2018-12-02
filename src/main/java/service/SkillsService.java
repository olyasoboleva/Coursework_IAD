package service;

import entity.*;

import java.util.List;
import java.util.Map;

public interface SkillsService {

    /**
     * find skill by id
     * @param skillId id
     * @return skill
     */
    SkillsEntity getSkillById(int skillId);
    /**
     * find default skill of district
     * @param districtsEntity district
     * @return skill
     */
    SkillsEntity getSkillByDistrict(DistrictsEntity districtsEntity);


    /**
     * find skill which level this training increase
     * @param training training
     * @return skill
     */
    SkillsEntity getSkillByTraining(TrainingsEntity training);

    /**
     * find all skills of user
     * @param user user
     * @return list of skills
     */
    List<SkillsEntity> getSkillsByUser(UsersEntity user);

    /**
     * find skill necessary for this weapon
     * @param weapon weapon
     * @return skill
     */
    SkillsEntity getSkillByWeapon(WeaponsEntity weapon);

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
