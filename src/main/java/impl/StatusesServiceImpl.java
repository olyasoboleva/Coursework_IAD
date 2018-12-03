package impl;

import entity.Status;
import org.springframework.transaction.annotation.Transactional;
import repository.StatusesRepository;
import service.StatusesService;

public class StatusesServiceImpl implements StatusesService {

    private final StatusesRepository statusesRepository;

    public StatusesServiceImpl(StatusesRepository statusesRepository) {
        this.statusesRepository = statusesRepository;
    }

    @Transactional
    @Override
    public Status updateStatuses(Status status) {
        statusesRepository.save(status);
        return status;
    }

    @Override
    public Status createStatus(Status status) {
        statusesRepository.save(status);
        return status;
    }

    @Override
    public boolean deleteStatus(Status status) {
        statusesRepository.delete(status);
        return true;
    }

    @Override
    public Status getStatuseById(int statusId) {
        return statusesRepository.findStatusByStatusId(statusId);
    }

    @Override
    public Status getStatuseByName(String name) {
        return statusesRepository.findStatuseByName(name);
    }
}
