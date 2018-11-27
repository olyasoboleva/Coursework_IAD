package impl;

import repository.DistrictsRepository;
import service.DistrictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("districtsService")
public class DistrictsServiceImpl  implements DistrictsService{
    private final DistrictsRepository districtsRepository;

    @Autowired
    public DistrictsServiceImpl(DistrictsRepository districtsRepository) {
        this.districtsRepository = districtsRepository;
    }

}
