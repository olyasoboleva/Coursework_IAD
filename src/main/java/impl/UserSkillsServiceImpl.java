package impl;

import entity.Skill;
import entity.UserSkill;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserSkillsRepository;
import service.UserSkillsService;

import java.util.List;

@Service("userSkillsService")
public class UserSkillsServiceImpl implements UserSkillsService {

    private final UserSkillsRepository userSkillsRepository;

    @Autowired
    public UserSkillsServiceImpl(UserSkillsRepository userSkillsRepository) {
        this.userSkillsRepository = userSkillsRepository;
    }

    @Override
    public UserSkill createUserSkill(UserSkill userSkill) {
        userSkillsRepository.save(userSkill);
        return userSkill;
    }

    @Override
    public boolean deleteUserSkill(UserSkill userSkill) {
        userSkillsRepository.delete(userSkill);
        return true;
    }

    @Override
    public UserSkill updateUserSkills(UserSkill userSkill) {
        userSkillsRepository.save(userSkill);
        return userSkill;
    }

    @Override
    public UserSkill getUserSkillByUserAndSkill(User user, Skill skill) {
        return userSkillsRepository.findUserSkillByUserAndSkill(user, skill);
    }

    @Override
    public List<UserSkill> getUserSkillsByUser(User user) {
        return userSkillsRepository.getUserSkillsByUser(user);
    }
}
