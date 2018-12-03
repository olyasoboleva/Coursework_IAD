package service;

import entity.Skill;
import entity.UserSkill;
import entity.User;

import java.util.List;

public interface UserSkillService {
    /**
     * It creates new user skill
     * @param userSkill skill and user
     * @return new relation if it was created
     */
    UserSkill createUserSkill(UserSkill userSkill);

    /**
     * It deletes user skill
     * @param userSkill user and skill
     * @return true if it was successfully deleted
     */
    boolean deleteUserSkill(UserSkill userSkill);

    /**
     * It updates user skill
     * @param userSkill user and skill
     * @return tris relation it was correctly updated
     */
    UserSkill updateUserSkills(UserSkill userSkill);

    /**
     * find user's level of skill
     *
     * @param user user
     * @param skill skill
     * @return user's skill with level
     */
    UserSkill getUserSkillByUserAndSkill(User user, Skill skill);

    /**
     * find user's skills
     *
     * @param user user
     * @return list of skills with level
     */
    List<UserSkill> getUserSkillsByUser(User user);
}
