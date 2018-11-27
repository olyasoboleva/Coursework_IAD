package impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TrainingsRepository;
import service.TrainingsService;

@Service("trainingsService")
public class TrainingsServiceImpl implements TrainingsService {

    private final TrainingsRepository trainingsRepository;

    @Autowired
    public TrainingsServiceImpl(TrainingsRepository trainingsRepository) {
        this.trainingsRepository = trainingsRepository;
    }

}
