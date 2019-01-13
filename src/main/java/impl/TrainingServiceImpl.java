package impl;

import entity.Training;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TrainingRepository;
import service.TrainingService;

import java.util.List;

@Service("trainingService")
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public boolean deleteTraining(Training training) {
        trainingRepository.delete(training);
        return true;
    }

    @Override
    public Training updateTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public Training getTrainingById(int trainingId) {
        return trainingRepository.findTrainingByTrainingId(trainingId);
    }

    @Override
    public List<Training> getTrainingsByDayOfWeek(int dayOfWeek) {
        return trainingRepository.getTrainingsByDayOfWeek(dayOfWeek);
    }

    @Override
    public Training getTrainingByName(String name) {
        return trainingRepository.findTrainingByName(name);
    }
}
