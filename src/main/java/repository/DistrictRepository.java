package repository;

import entity.District;
import org.springframework.data.repository.CrudRepository;

public interface DistrictRepository extends CrudRepository<District, Integer> {
    /**
     * find district by id
     * @param districtId id
     * @return district
     */
    District findDistrictByDistrictId(int districtId);
}
