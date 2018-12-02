package impl;

import entity.DistrictsEntity;
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

    @Override
    public DistrictsEntity getDistrictById(int districtId) {
        return districtsRepository.findDistrictsEntityByDistrictId(districtId);
    }

    @Override
    public DistrictsEntity createDistrict(DistrictsEntity district) {
        districtsRepository.save(district);
        return district;
    }

    @Override
    public DistrictsEntity updateDistrict(DistrictsEntity district) {
        districtsRepository.save(district);
        return district;
    }

    @Override
    public boolean deleteDistrict(DistrictsEntity district) {
        districtsRepository.delete(district);
        return true;
    }
}
