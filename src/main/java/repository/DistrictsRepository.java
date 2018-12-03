package repository;

import entity.District;
import org.springframework.data.repository.CrudRepository;

public interface DistrictsRepository extends CrudRepository<District, Integer> {
    /**
     * find district by id
     * @param districtId id
     * @return district
     */
    District findDistrictByDistrictId(int districtId);
}
