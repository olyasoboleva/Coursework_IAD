package repository;

import entity.Skill;
import entity.User;
import entity.UserSkill;
import entity.UserSkillPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserSkillsRepository extends CrudRepository<UserSkill, Integer> {
    /**
     * find user's level of skill
     *
     * @param user user
     * @param skill skill
     * @return user's skill with level
     */
    UserSkill findUserSkillByUserAndSkill(User user, Skill skill);

    /**
     * find user's skills
     *
     * @param user user
     * @return list of skills with level
     */
    List<UserSkill> getUserSkillsByUser(User user);
}
