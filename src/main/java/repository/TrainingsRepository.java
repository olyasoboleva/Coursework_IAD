package repository;

import entity.SkillsEntity;
import entity.TrainingsEntity;
import entity.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.List;

public interface TrainingsRepository extends CrudRepository<TrainingsEntity, Integer> {
    /**
     * find training by id
     * @param trainingid id
     * @return training
     */
    TrainingsEntity findTrainingsEntityByTrainingid(int trainingid);

    /**
     * find training on this day of week
     * @param dayofweek day of week
     * @return list of trainings
     */
    List<TrainingsEntity> getTrainingsEntityByDayofweek(Short dayofweek);

    //FIXME: а оно надо?
    List<TrainingsEntity> getTrainingsEntityByTimeoftraining(Time timeoftraining);

    //FIXME: и это зачем?
    List<TrainingsEntity> getTrainingsEntityBySkillsBySkillid(SkillsEntity skill);

    /**
     * find schedule of trainer
     * @param trainer trainer
     * @return list of trainings
     */
    List<TrainingsEntity> getTrainingsEntityByUsersByTrainer(UsersEntity trainer);
}
