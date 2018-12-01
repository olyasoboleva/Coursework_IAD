package impl;

import entity.UserSkillsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserSkillsRepository;
import service.UserSkillsService;

@Service("userSkillsService")
public class UserSkillsServiceImpl implements UserSkillsService {

    private final UserSkillsRepository userSkillsRepository;

    @Autowired
    public UserSkillsServiceImpl(UserSkillsRepository userSkillsRepository) {
        this.userSkillsRepository = userSkillsRepository;
    }

    @Override
    public UserSkillsEntity createUserSkill(UserSkillsEntity userSkill) {
        userSkillsRepository.save(userSkill);
        return userSkill;
    }

    @Override
    public boolean deleteUserSkill(UserSkillsEntity userSkill) {
        userSkillsRepository.delete(userSkill);
        return true;
    }

    @Override
    public UserSkillsEntity updateUserSkills(UserSkillsEntity userSkill) {
        userSkillsRepository.save(userSkill);
        return userSkill;
    }
}
