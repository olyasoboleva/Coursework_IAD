package repository;

import entity.SkillsEntity;
import entity.UsersEntity;
import entity.UserSkillsEntity;
import entity.UserSkillsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSkillsRepository extends JpaRepository<UserSkillsEntity, UserSkillsEntityPK> {
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
