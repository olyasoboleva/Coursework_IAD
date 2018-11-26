package repository;

import entity.DistrictsEntity;
import entity.SkillsEntity;
import entity.TrainingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillsRepository extends JpaRepository<SkillsEntity,Integer> {
    SkillsEntity findSkillsEntityBySkillid(int skillid);
    /**
     * Получение скилла, который связан с дистриктом
     * @param districtsEntity дистрикт
     * @return
     */
    SkillsEntity findSkillsEntityByDistrictsBySkillid(DistrictsEntity districtsEntity);


    /**
     * Получить скилл, который увеличивается после прохождения данной тренировки
     * @param training тренировка
     * @return skills
     */
    SkillsEntity findSkillsEntityByTrainingsBySkillid(TrainingsEntity training);


    //TODO: я чёт сломалась на этом запросе, присоединяя userskills, а потом user
    @Query("select skill from SkillsEntity skill join UserskillsEntity  userskill")
    List<SkillsEntity> findSkillsByUser(@Param("userID")int userID);



}
