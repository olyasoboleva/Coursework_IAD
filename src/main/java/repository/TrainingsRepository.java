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
    TrainingsEntity findTrainingsEntityByTrainingid(int trainingid);
    List<TrainingsEntity> getTrainingsEntityByDayofweek(Short dayofweek);
    List<TrainingsEntity> getTrainingsEntityByTimeoftraining(Time timeoftraining);
    List<TrainingsEntity> getTrainingsEntityBySkillsBySkillid(SkillsEntity skill);
    List<TrainingsEntity> getTrainingsEntityByUsersByTrainer(UsersEntity trainer);
}
