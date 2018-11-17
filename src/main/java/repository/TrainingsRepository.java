package repository;

import entity.TrainingsEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Time;
import java.util.List;

public interface TrainingsRepository extends CrudRepository<TrainingsEntity, Integer> {
    TrainingsEntity findTrainingsEntityByTrainingid(int trainingid);
    //TODO:я думаю, что надо искать по скиллам, потому что нам коэффициент понадобится для определённого скилла, чтобы увеличтить его
    //там ещё есть сущность, которая должна увеличивать скилл сама, но вообще на это триггер есть
    //или не надо это
    List<TrainingsEntity> getTrainingsEntityByDayofweek(Short dayofweek);
    List<TrainingsEntity> getTrainingsEntityByTimeoftraining(Time timeoftrainig);
    List<TrainingsEntity> getTrainingsEntityBySkillid(Integer skillid);
    List<TrainingsEntity> getTrainingsEntityByTrainer(Integer trainer);
}
