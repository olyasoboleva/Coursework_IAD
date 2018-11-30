package repository;

import entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface SkillsRepository extends JpaRepository<SkillsEntity,Integer> {
    /**
     * find skill by id
     * @param skillid id
     * @return skill
     */
    SkillsEntity findSkillsEntityBySkillid(int skillid);
    /**
     * find default skill of district
     * @param districtsEntity district
     * @return skill
     */
    SkillsEntity findSkillsEntityByDistrictsBySkillid(DistrictsEntity districtsEntity);


    /**
     * find skill, which level this training increase
     * @param training training
     * @return skill
     */
    SkillsEntity findSkillsEntityByTrainingsBySkillid(TrainingsEntity training);

    /**
     * find all skills of user
     * @param user user
     * @return list of skills
     */
    List<SkillsEntity> findSkillsEntitiesByUsers(UsersEntity user);

    /**
     * find skill necessary for this weapon
     * @param weapon weapon
     * @return skill
     */
    SkillsEntity findSkillsEntitiesByWeaponsByWeaponid(WeaponsEntity weapon);

}
