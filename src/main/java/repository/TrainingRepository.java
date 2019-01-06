package repository;

import entity.Training;
import entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainingRepository extends CrudRepository<Training, Integer> {
    /**
     * find training by id
     * @param trainingId id
     * @return training
     */
    Training findTrainingByTrainingId(int trainingId);

    Training findTrainingByName(String name);

    /**
     * find training on this day of week
     * @param dayOfWeek day of week number
     * @return list of trainings
     */
    List<Training> getTrainingsByDayOfWeek(int dayOfWeek);

    /**
     * find schedule of trainer
     * @param trainer trainer
     * @return list of trainings
     */
    List<Training> getTrainingsByTrainer(User trainer);
}
