package impl;

import entity.SkillsEntity;
import entity.UserSkillsEntity;
import entity.UsersEntity;
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

    @Override
    public UserSkillsEntity getUserSkillByUserAndSkill(UsersEntity user, SkillsEntity skill) {
        return userSkillsRepository.findUserSkillsEntityByUserAndSkill(user, skill);
    }

    @Override
    public List<UserSkillsEntity> getUserSkillsByUser(UsersEntity user) {
        return userSkillsRepository.getUserSkillsEntitiesByUser(user);
    }
}
