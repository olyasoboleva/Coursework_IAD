package repository;

import entity.SkillsEntity;
import entity.UsersEntity;
import entity.UserskillsEntity;
import entity.UserskillsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserskillsRepository extends JpaRepository<UserskillsEntity, UserskillsEntityPK> {
    /**
     * Получить уровень скилла пользователя
     * @param user пользователь
     * @param skill скилл
     * @return сущность, с которой можно получить уровень
     */
    UserskillsEntity findUserskillsEntityByUsersByUseridAndSkillsBySkillid(UsersEntity user, SkillsEntity skill);
    List<UserskillsEntity> getUserskillsEntitiesByUsersByUserid(UsersEntity user);
}
