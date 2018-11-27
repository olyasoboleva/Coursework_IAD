package impl;

import entity.SkillsEntity;
import entity.TributesEntity;
import entity.UsersEntity;
import entity.UserskillsEntity;
import org.springframework.transaction.annotation.Transactional;
import repository.SkillsRepository;
import repository.UserRepository;
import repository.UserskillsRepository;
import service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("skillsService")
public class SkillsServiceImpl implements SkillsService {

    private final SkillsRepository skillsRepository;
    private final UserskillsRepository userskillsRepository;
    private final UserRepository userRepository;

    @Autowired
    public SkillsServiceImpl(SkillsRepository skillsRepository, UserskillsRepository userskillsRepository, UserRepository userRepository) {
        this.skillsRepository = skillsRepository;
        this.userskillsRepository = userskillsRepository;
        this.userRepository = userRepository;
    }


      /**
       * Получить все скиллы пользователя
       * @param user пользователь
       * @return скиллы и их уровень
       */
      @Transactional
      @Override
    public Map<SkillsEntity, Short> getAllUserSkills(UsersEntity user) {
        Map<SkillsEntity, Short> allUserSkills = new HashMap<>();
        List<UserskillsEntity> userSkills = userskillsRepository.getUserskillsEntitiesByUsersByUserid(user);
        List<SkillsEntity> allSkills = skillsRepository.findAll();
        for (SkillsEntity skill : allSkills) {
            for (UserskillsEntity userSkill : userSkills) {
                if (skill.getSkillid() == userSkill.getSkillid()) {
                    allUserSkills.put(skill,userSkill.getLevelofskill());
                }
            }
        }
        return allUserSkills;
    }

    /**
     * Получение всех скиллов трибута
     * @param tribute трибут
     * @return скиллы и их уровень
     */
    @Transactional
    @Override
    public Map<SkillsEntity, Short> getAllTributeSkills(TributesEntity tribute) {
          UsersEntity user = userRepository.findUsersEntityByTributesByUserid(tribute);
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
