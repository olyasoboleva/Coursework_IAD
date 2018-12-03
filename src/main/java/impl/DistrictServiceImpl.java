package impl;

import entity.District;
import repository.DistrictRepository;
import service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("districtService")
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public District getDistrictById(int districtId) {
        return districtRepository.findDistrictByDistrictId(districtId);
    }

    @Override
    public District createDistrict(District district) {
        districtRepository.save(district);
        return district;
    }

    @Override
    public District updateDistrict(District district) {
        districtRepository.save(district);
        return district;
    }

    @Override
    public boolean deleteDistrict(District district) {
        districtRepository.delete(district);
        return true;
    }
}
