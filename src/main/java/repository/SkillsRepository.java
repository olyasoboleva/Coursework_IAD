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
     * @return скилл
     */
    SkillsEntity findSkillsEntityByDistrictsBySkillid(DistrictsEntity districtsEntity);


    /**
     * Получить скилл, который увеличивается после прохождения данной тренировки
     * @param training тренировка
     * @return skills
     */
    SkillsEntity findSkillsEntityByTrainingsBySkillid(TrainingsEntity training);


}
