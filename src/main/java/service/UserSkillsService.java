package service;

import entity.SkillsEntity;
import entity.UserSkillsEntity;
import entity.UsersEntity;

import java.util.List;

public interface UserSkillsService {
    /**
     * It creates new user skill
     * @param userSkill skill and user
     * @return new relation if it was created
     */
    UserSkillsEntity createUserSkill(UserSkillsEntity userSkill);

    /**
     * It deletes user skill
     * @param userSkill user and skill
     * @return true if it was successfully deleted
     */
    boolean deleteUserSkill(UserSkillsEntity userSkill);

    /**
     * It updates user skill
     * @param userSkill user and skill
     * @return tris relation it was correctly updated
     */
    UserSkillsEntity updateUserSkills(UserSkillsEntity userSkill);

    /**
     * find user's level of skill
     *
     * @param user user
     * @param skill skill
     * @return user's skill with level
     */
    UserSkillsEntity getUserSkillByUserAndSkill(UsersEntity user, SkillsEntity skill);

    /**
     * find user's skills
     *
     * @param user user
     * @return list of skills with level
     */
    List<UserSkillsEntity> getUserSkillsByUser(UsersEntity user);
}
