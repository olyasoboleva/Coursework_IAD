package repository;

import entity.SkillsEntity;
import org.springframework.data.repository.CrudRepository;

public interface SkillsRepository extends CrudRepository<SkillsEntity,Integer> {
    SkillsEntity findSkillsEntityBySkillid(int skillid);
    //TODO: надо доделать и обновить сущность
}
