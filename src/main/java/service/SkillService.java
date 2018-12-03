package service;

import entity.*;

import java.util.List;
import java.util.Map;

public interface SkillService {

    /**
     * find skill by id
     * @param skillId id
     * @return skill
     */
    Skill getSkillById(int skillId);
    /**
     * find default skill of district
     * @param district district
     * @return skill
     */
    Skill getSkillByDistrict(District district);


    /**
     * find skill which level this training increase
     * @param training training
     * @return skill
     */
    Skill getSkillByTraining(Training training);

    /**
     * find all skills of user
     * @param user user
     * @return list of skills
     */
    List<Skill> getSkillsByUser(User user);

    /**
     * find skill necessary for this weapon
     * @param weapon weapon
     * @return skill
     */
    Skill getSkillByWeapon(Weapon weapon);

    /**
     * It creates new skill
     * @param skill skill
     * @return new skill if it was created
     */
    Skill createSkill(Skill skill);

    /**
     * It updates sime skill
     * @param skill skill
     * @return skill if it was correctly updated
     */
    Skill updateSkill(Skill skill);

    /**
     * It deletes skill
     * @param skill skill
     * @return true if it was successfully deleted
     */
    boolean deleteSkill(Skill skill);

    /**
     * Try to find all user skills
     * @param user user
     * @return his skills
     */
    Map<Skill, Integer> getAllUserSkills(User user);

    /**
     * Try to find all tribute skills
     * @param tribute tribute
     * @return his skills
     */
    Map<Skill, Integer> getAllTributeSkills(Tribute tribute);
}
