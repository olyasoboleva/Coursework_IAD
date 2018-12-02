package service;

import entity.TrainingsEntity;
import entity.UsersEntity;

import java.util.List;

public interface TrainingsService {

    /**
     * It creates new training
     * @param training training
     * @return new training if it was saved
     */
    TrainingsEntity createTraining(TrainingsEntity training);

    /**
     * It deletes training
     * @param training training
     * @return true if it was correctly deleted
     */
    boolean deleteTraining(TrainingsEntity training);

    /**
     * It updates the training
     * @param training training
     * @return training if it was updated correctly
     */
    TrainingsEntity updateTraining(TrainingsEntity training);

    /**
     * find training by id
     * @param trainingId id
     * @return training
     */
    TrainingsEntity getTrainingById(int trainingId);

    /**
     * find training on this day of week
     * @param dayOfWeek day of week number
     * @return list of trainings
     */
    List<TrainingsEntity> getTrainingsByDayOfWeek(int dayOfWeek);

    /**
     * find schedule of trainer
     * @param trainer trainer
     * @return list of trainings
     */
    List<TrainingsEntity> getTrainingsByTrainer(UsersEntity trainer);

}
