package repository;

import entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SkillRepository extends CrudRepository<Skill,Integer> {
    /**
     * find skill by id
     * @param skillId id
     * @return skill
     */
    Skill findSkillBySkillId(int skillId);
    /**
     * find default skill of district
     * @param district district
     * @return skill
     */
    Skill findSkillByDistrict(District district);


    /**
     * find skill which level this training increase
     * @param training training
     * @return skill
     */
    Skill findSkillByTrainings(Training training);

    /**
     * find all skills of user
     * @param user user
     * @return list of skills
     */
    List<Skill> findSkillsByUsers(User user);

    /**
     * find skill necessary for this weapon
     * @param weapon weapon
     * @return skill
     */
    Skill findSkillByWeapon(Weapon weapon);

}
