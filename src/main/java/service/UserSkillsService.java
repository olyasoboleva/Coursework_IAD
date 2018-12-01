package service;

import entity.UserSkillsEntity;

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
}
