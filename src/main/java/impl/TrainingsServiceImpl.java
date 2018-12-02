package impl;

import entity.TrainingsEntity;
import entity.UsersEntity;
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
    public TrainingsEntity createTraining(TrainingsEntity training) {
        trainingsRepository.save(training);
        return training;
    }

    @Override
    public boolean deleteTraining(TrainingsEntity training) {
        trainingsRepository.delete(training);
        return true;
    }

    @Override
    public TrainingsEntity updateTraining(TrainingsEntity training) {
        trainingsRepository.save(training);
        return training;
    }

    @Override
    public TrainingsEntity getTrainingById(int trainingId) {
        return trainingsRepository.findTrainingsEntityByTrainingId(trainingId);
    }

    @Override
    public List<TrainingsEntity> getTrainingsByDayOfWeek(int dayOfWeek) {
        return trainingsRepository.getTrainingsEntityByDayOfWeek(dayOfWeek);
    }

    @Override
    public List<TrainingsEntity> getTrainingsByTrainer(UsersEntity trainer) {
        return trainingsRepository.getTrainingsEntityByTrainer(trainer);
    }
}
