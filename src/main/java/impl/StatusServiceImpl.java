package impl;

import entity.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.StatusRepository;
import service.StatusService;

@Service("statusService")
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Transactional
    @Override
    public Status updateStatuses(Status status) {
        statusRepository.save(status);
        return status;
    }

    @Override
    public Status createStatus(Status status) {
        statusRepository.save(status);
        return status;
    }

    @Override
    public boolean deleteStatus(Status status) {
        statusRepository.delete(status);
        return true;
    }

    @Override
    public Status getStatuseById(int statusId) {
        return statusRepository.findStatusByStatusId(statusId);
    }

    @Override
    public Status getStatuseByName(String name) {
        return statusRepository.findStatuseByName(name);
    }
}
