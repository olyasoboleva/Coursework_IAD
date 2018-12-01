package impl;

import entity.SkillsEntity;
import entity.TributesEntity;
import entity.UsersEntity;
import entity.UserSkillsEntity;
import org.springframework.transaction.annotation.Transactional;
import repository.SkillsRepository;
import repository.UserRepository;
import repository.UserSkillsRepository;
import service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("skillsService")
public class SkillsServiceImpl implements SkillsService {

    private final SkillsRepository skillsRepository;
    private final UserSkillsRepository userSkillsRepository;
    private final UserRepository userRepository;

    @Autowired
    public SkillsServiceImpl(SkillsRepository skillsRepository, UserSkillsRepository userSkillsRepository, UserRepository userRepository) {
        this.skillsRepository = skillsRepository;
        this.userSkillsRepository = userSkillsRepository;
        this.userRepository = userRepository;
    }



      @Transactional
      @Override
    public Map<SkillsEntity, Integer> getAllUserSkills(UsersEntity user) {
        Map<SkillsEntity, Integer> allUserSkills = new HashMap<>();
        List<UserSkillsEntity> userSkills = userSkillsRepository.getUserSkillsEntitiesByUser(user);
        List<SkillsEntity> allSkills = skillsRepository.findAll();
        for (SkillsEntity skill : allSkills) {
            for (UserSkillsEntity userSkill : userSkills) {
                if (skill.getSkillId() == userSkill.getSkillId()) {
                    allUserSkills.put(skill,userSkill.getLevelOfSkill());
                }
            }
        }
        return allUserSkills;
    }


    @Transactional
    @Override
    public Map<SkillsEntity, Integer> getAllTributeSkills(TributesEntity tribute) {
          UsersEntity user = userRepository.findUsersEntityByTributesByUser(tribute);
        return getAllUserSkills(user);
    }


    @Transactional
    @Override
    public SkillsEntity createSkill(SkillsEntity skill) {
        skillsRepository.save(skill);
        return skill;
    }

    @Transactional
    @Override
    public SkillsEntity updateSkill(SkillsEntity skill) {
        skillsRepository.save(skill);
        return skill;
    }

    @Transactional
    @Override
    public boolean deleteSkill(SkillsEntity skill) {
        skillsRepository.delete(skill);
        return true;
    }

}
