package repository;

import entity.SkillsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillsRepository extends CrudRepository<SkillsEntity,Integer> {
    SkillsEntity findSkillsEntityBySkillid(int skillid);
    //TODO: надо доделать и обновить сущность

    @Query(value = "select skill from DistrictsEntity district join SkillsEntity skill on(district.skillid = skill.skillid) where district.skillid = :skillID")
    SkillsEntity findSkillsBySkillid(@Param("skillID")int skillID);
}
