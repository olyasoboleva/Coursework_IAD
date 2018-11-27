package impl;

import entity.SkillsEntity;
import entity.UserskillsEntity;
import org.springframework.transaction.annotation.Transactional;
import repository.SkillsRepository;
import repository.UserskillsRepository;
import service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("skillsService")
public class SkillsServiceImpl implements SkillsService {

    private final SkillsRepository skillsRepository;
    private final UserskillsRepository userskillsRepository;

    @Autowired
    public SkillsServiceImpl(SkillsRepository skillsRepository, UserskillsRepository userskillsRepository) {
        this.skillsRepository = skillsRepository;
        this.userskillsRepository = userskillsRepository;
    }

    /* FIXME: я и тут попробовала сделать двойной join, но нужна помощь
     .//еще я поменяла два репозитория с Crud на jpa, потому что иначе такой stream() не сделать
      /**
       * Получить все скиллы пользователя
       * @param userID пользователь
       * @return скиллы
       */
    public List<SkillsEntity> getAllUserSkills(int userID) {
        //List<UserskillsEntity> list = userskillsRepository.getUserskillsEntitiesByUserid(userID);
        //return userskillsRepository.findAll().stream().filter(userskillsEntity -> userskillsEntity.getUserid() == userID).collect(Collectors.toList());
    return null;

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
