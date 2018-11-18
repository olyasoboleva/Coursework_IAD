package repository;

import entity.TrainingsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.List;

public interface TrainingsRepository extends CrudRepository<TrainingsEntity, Integer> {
    TrainingsEntity findTrainingsEntityByTrainingid(int trainingid);
    //TODO:я думаю, что надо искать по скиллам, потому что нам коэффициент понадобится для определённого скилла, чтобы увеличить его
    //там ещё есть сущность, которая должна увеличивать скилл сама, но вообще на это триггер есть
    //или не надо это
    List<TrainingsEntity> getTrainingsEntityByDayofweek(Short dayofweek);
    List<TrainingsEntity> getTrainingsEntityByTimeoftraining(Time timeoftraining);
    List<TrainingsEntity> getTrainingsEntityBySkillid(Integer skillid);
    List<TrainingsEntity> getTrainingsEntityByTrainer(Integer trainer);


    /**
     * Получить все тренировки, которые проводятся для повышения этого скилла
     * @param skillID скилл
     * @return тренировки
     */
    @Query("select training from TrainingsEntity  training join SkillsEntity skill on(training.skillid = skill.skillid) where skill.skillid = :skillID")
    List<TrainingsEntity> getTrainingBySkill(@Param("skillID")int skillID );
}
