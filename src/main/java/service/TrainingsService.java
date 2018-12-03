package service;

import entity.Training;
import entity.User;

import java.util.List;

public interface TrainingsService {

    /**
     * It creates new training
     * @param training training
     * @return new training if it was saved
     */
    Training createTraining(Training training);

    /**
     * It deletes training
     * @param training training
     * @return true if it was correctly deleted
     */
    boolean deleteTraining(Training training);

    /**
     * It updates the training
     * @param training training
     * @return training if it was updated correctly
     */
    Training updateTraining(Training training);

    /**
     * find training by id
     * @param trainingId id
     * @return training
     */
    Training getTrainingById(int trainingId);

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
