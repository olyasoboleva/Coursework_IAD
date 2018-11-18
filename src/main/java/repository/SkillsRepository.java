package repository;

import entity.SkillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillsRepository extends JpaRepository<SkillsEntity,Integer> {
    SkillsEntity findSkillsEntityBySkillid(int skillid);

//FIXME: что-то тут не так? в этом запросе
    /**
     * Получение скилла, который связан с дистриктом
     * @param skillID скилл
     * @return
     */
    @Query(value = "select skill from DistrictsEntity district join SkillsEntity skill on(district.skillid = skill.skillid) where district.skillid = :skillID")
    SkillsEntity findSkillsBySkillid(@Param("skillID")int skillID);



    ///////////

    /**
     * Получить скилл, который увелисивается после прохождения данной тренировки
     * @param trainingID тренировка
     * @return скилл
     */
    @Query("select  skill from SkillsEntity skill join TrainingsEntity training on(skill.skillid = training.skillid) where training.trainingid = :trainingID")
    SkillsEntity findSkillByTraining(@Param("trainingID")int trainingID);


    //TODO: я чёт сломалась на этом запросе, присоединяя userskills, а потом user
    @Query("select skill from SkillsEntity skill join UserskillsEntity  userskill")
    List<SkillsEntity> findSkillsByUser(@Param("userID")int userID);



}
