package repository;

import entity.UserskillsEntity;
import entity.UserskillsEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserskillsRepository extends CrudRepository<UserskillsEntity, UserskillsEntityPK> {
    UserskillsEntity findUserskillsEntityByUseridAndAndSkillid(int userId, int skillId);
    List<UserskillsEntity> getUserskillsEntitiesByUserid(int id);
}
