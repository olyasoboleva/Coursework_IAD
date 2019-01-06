package impl;

import entity.*;
import org.springframework.transaction.annotation.Transactional;
import repository.SkillRepository;
import repository.UserRepository;
import repository.UserSkillRepository;
import service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("skillService")
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;
    private final UserRepository userRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository, UserSkillRepository userSkillRepository, UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
        this.userRepository = userRepository;
    }


      @Transactional
      @Override
    public Map<Skill, Integer> getAllUserSkills(User user) {
        Map<Skill, Integer> allUserSkills = new HashMap<>();
        List<UserSkill> userSkills = userSkillRepository.getUserSkillsByUser(user);
        List<Skill> allSkills = (List<Skill>) skillRepository.findAll();
        for (Skill skill : allSkills) {
            for (UserSkill userSkill : userSkills) {
                if (skill.equals(userSkill.getSkill())) {
                    allUserSkills.put(skill,userSkill.getLevelOfSkill());
                }
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
        return skillRepository.findSkillBySkillId(skillId);
    }

    @Override
    public Skill getSkillByDistrict(District district) {
        return skillRepository.findSkillByDistrict(district);
    }

    @Override
    public Skill getSkillByTraining(Training training) {
        return skillRepository.findSkillByTrainings(training);
    }

    @Override
    public List<Skill> getSkillsByUser(User user) {
        return skillRepository.findSkillsByUsers(user);
    }

    @Override
    public Skill getSkillByWeapon(Weapon weapon) {
        return skillRepository.findSkillByWeapon(weapon);
    }

    @Transactional
    @Override
    public Skill createSkill(Skill skill) {
        skillRepository.save(skill);
        return skill;
    }

    @Transactional
    @Override
    public Skill updateSkill(Skill skill) {
        skillRepository.save(skill);
        return skill;
    }

    @Transactional
    @Override
    public boolean deleteSkill(Skill skill) {
        skillRepository.delete(skill);
        return true;
    }

}
