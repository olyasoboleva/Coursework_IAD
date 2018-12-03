package impl;

import entity.Training;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TrainingsRepository;
import service.TrainingsService;

import java.util.List;

@Service("trainingsService")
public class TrainingsServiceImpl implements TrainingsService {

    private final TrainingsRepository trainingsRepository;

    @Autowired
    public TrainingsServiceImpl(TrainingsRepository trainingsRepository) {
        this.trainingsRepository = trainingsRepository;
    }

    @Override
    public Training createTraining(Training training) {
        trainingsRepository.save(training);
        return training;
    }

    @Override
    public boolean deleteTraining(Training training) {
        trainingsRepository.delete(training);
        return true;
    }

    @Override
    public Training updateTraining(Training training) {
        trainingsRepository.save(training);
        return training;
    }

    @Override
    public Training getTrainingById(int trainingId) {
        return trainingsRepository.findTrainingByTrainingId(trainingId);
    }

    @Override
    public List<Training> getTrainingsByDayOfWeek(int dayOfWeek) {
        return trainingsRepository.getTrainingsByDayOfWeek(dayOfWeek);
    }

    @Override
    public List<Training> getTrainingsByTrainer(User trainer) {
        return trainingsRepository.getTrainingsByTrainer(trainer);
    }
}
