package impl;

import entity.Skill;
import entity.UserSkill;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserSkillRepository;
import service.UserSkillService;

import java.util.List;

@Service("userSkillService")
public class UserSkillServiceImpl implements UserSkillService {

    private final UserSkillRepository userSkillRepository;

    @Autowired
    public UserSkillServiceImpl(UserSkillRepository userSkillRepository) {
        this.userSkillRepository = userSkillRepository;
    }

    @Override
    public UserSkill createUserSkill(UserSkill userSkill) {
        userSkillRepository.save(userSkill);
        return userSkill;
    }

    @Override
    public boolean deleteUserSkill(UserSkill userSkill) {
        userSkillRepository.delete(userSkill);
        return true;
    }

    @Override
    public UserSkill updateUserSkills(UserSkill userSkill) {
        userSkillRepository.save(userSkill);
        return userSkill;
    }

    @Override
    public UserSkill getUserSkillByUserAndSkill(User user, Skill skill) {
        return userSkillRepository.findUserSkillByUserAndSkill(user, skill);
    }

    @Override
    public List<UserSkill> getUserSkillsByUser(User user) {
        return userSkillRepository.getUserSkillsByUser(user);
    }
}
