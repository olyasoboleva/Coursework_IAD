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
     * find user's level of skill
     *
     * @param user user
     * @param skill skill
     * @return user's skill with level
     */
    UserskillsEntity findUserskillsEntityByUsersByUseridAndSkillsBySkillid(UsersEntity user, SkillsEntity skill);

    /**
     * find user's skills
     *
     * @param user user
     * @return list of skills with level
     */
    List<UserskillsEntity> getUserskillsEntitiesByUsersByUserid(UsersEntity user);
}
