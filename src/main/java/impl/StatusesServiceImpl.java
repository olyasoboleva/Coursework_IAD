package impl;

import entity.StatusesEntity;
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
    public StatusesEntity updateStatuses(StatusesEntity status) {
        statusesRepository.save(status);
        return status;
    }

    @Override
    public StatusesEntity createStatus(StatusesEntity status) {
        statusesRepository.save(status);
        return status;
    }

    @Override
    public boolean deleteStatus(StatusesEntity status) {
        statusesRepository.delete(status);
        return true;
    }

    @Override
    public StatusesEntity getStatuseById(int statusId) {
        return statusesRepository.findStatusesEntityByStatusId(statusId);
    }

    @Override
    public StatusesEntity getStatuseByName(String name) {
        return statusesRepository.findStatusesEntityByName(name);
    }
}
