package repository;

import entity.SkillsEntity;
import entity.UsersEntity;
import entity.UserSkillsEntity;
import entity.UserSkillsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserSkillsRepository extends CrudRepository<UserSkillsEntity, UserSkillsEntityPK> {
    /**
     * find user's level of skill
     *
     * @param user user
     * @param skill skill
     * @return user's skill with level
     */
    UserSkillsEntity findUserSkillsEntityByUserAndSkill(UsersEntity user, SkillsEntity skill);

    /**
     * find user's skills
     *
     * @param user user
     * @return list of skills with level
     */
    List<UserSkillsEntity> getUserSkillsEntitiesByUser(UsersEntity user);
}
