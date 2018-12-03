package impl;

import entity.*;
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

    //FIXME: не тестила
      @Transactional
      @Override
    public Map<Skill, Integer> getAllUserSkills(User user) {
        Map<Skill, Integer> allUserSkills = new HashMap<>();
        List<UserSkill> userSkills = userSkillsRepository.getUserSkillsByUser(user);
        List<Skill> allSkills = (List<Skill>) skillsRepository.findAll(); //FIXME а это норм?
        for (Skill skill : allSkills) {
            for (UserSkill userSkill : userSkills) {
                //TODO: сломалось
                /*if (skill.getSkillId() == userSkill.getSkillId()) {
                    allUserSkills.put(skill,userSkill.getLevelOfSkill());
                }*/
            }
        }
        return allUserSkills;
    }


    @Transactional
    @Override
    public Map<Skill, Integer> getAllTributeSkills(Tribute tribute) {
          User user = userRepository.findUserByTributesByUser(tribute);
        return getAllUserSkills(user);
    }


    @Override
    public Skill getSkillById(int skillId) {
        return skillsRepository.findSkillBySkillId(skillId);
    }

    @Override
    public Skill getSkillByDistrict(District district) {
        return skillsRepository.findSkillByDistrict(district);
    }

    @Override
    public Skill getSkillByTraining(Training training) {
        return skillsRepository.findSkillByTrainings(training);
    }

    @Override
    public List<Skill> getSkillsByUser(User user) {
        return skillsRepository.findSkillsByUsers(user);
    }

    @Override
    public Skill getSkillByWeapon(Weapon weapon) {
        return skillsRepository.findSkillByWeapon(weapon);
    }

    @Transactional
    @Override
    public Skill createSkill(Skill skill) {
        skillsRepository.save(skill);
        return skill;
    }

    @Transactional
    @Override
    public Skill updateSkill(Skill skill) {
        skillsRepository.save(skill);
        return skill;
    }

    @Transactional
    @Override
    public boolean deleteSkill(Skill skill) {
        skillsRepository.delete(skill);
        return true;
    }

}
