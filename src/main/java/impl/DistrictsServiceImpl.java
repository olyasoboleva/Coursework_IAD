package impl;

import entity.District;
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
    public District getDistrictById(int districtId) {
        return districtsRepository.findDistrictByDistrictId(districtId);
    }

    @Override
    public District createDistrict(District district) {
        districtsRepository.save(district);
        return district;
    }

    @Override
    public District updateDistrict(District district) {
        districtsRepository.save(district);
        return district;
    }

    @Override
    public boolean deleteDistrict(District district) {
        districtsRepository.delete(district);
        return true;
    }
}
