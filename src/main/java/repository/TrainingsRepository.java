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
     * @param trainingId id
     * @return training
     */
    TrainingsEntity findTrainingsEntityByTrainingId(int trainingId);

    /**
     * find training on this day of week
     * @param dayOfWeek day of week
     * @return list of trainings
     */
    List<TrainingsEntity> getTrainingsEntityByDayOfWeek(int dayOfWeek);

    /**
     * find schedule of trainer
     * @param trainer trainer
     * @return list of trainings
     */
    List<TrainingsEntity> getTrainingsEntityByTrainer(UsersEntity trainer);
}
