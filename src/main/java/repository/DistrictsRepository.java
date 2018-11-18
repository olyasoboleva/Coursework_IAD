package repository;

import entity.DistrictsEntity;
import entity.SkillsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DistrictsRepository extends CrudRepository<DistrictsEntity, Integer> {
    DistrictsEntity findDistrictsEntityByDistrictid(int districtid);
}
