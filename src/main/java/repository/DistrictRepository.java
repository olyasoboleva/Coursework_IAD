package repository;

import entity.DistrictsEntity;
import org.springframework.data.repository.CrudRepository;

public interface DistrictRepository extends CrudRepository<DistrictsEntity, Integer> {
    DistrictsEntity findDistrictsEntityByDistrictid(short districtid);
}
