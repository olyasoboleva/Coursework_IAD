package repository;

import entity.UserskillsEntity;
import entity.UserskillsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserskillsRepository extends JpaRepository<UserskillsEntity, UserskillsEntityPK> {
    UserskillsEntity findUserskillsEntityByUseridAndSkillid(int userId, int skillId);
    List<UserskillsEntity> getUserskillsEntitiesByUserid(int id);


    /**
     * Получить уровень скилла пользователя
     * @param userID пользователь
     * @param skillID скилл
     * @return сущность, с которой можно получить уровень
     */
    @Query("select userSkill from UsersEntity userSkill join UsersEntity user on(userSkill.userid = user.userid) join SkillsEntity skill where user.userid = :userID and skill.skillid = :skillID")
    UserskillsEntity findUserSkillByUserAndSkill(@Param("userID")int userID, @Param("skillID")int skillID);
}
