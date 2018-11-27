package impl;

import entity.UserskillsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserskillsRepository;
import service.UserskillsService;

@Service("userSkillsService")
public class UserskillsServiceImpl implements UserskillsService{

    private final UserskillsRepository userskillsRepository;

    @Autowired
    public UserskillsServiceImpl(UserskillsRepository userskillsRepository) {
        this.userskillsRepository = userskillsRepository;
    }

    @Override
    public UserskillsEntity createUserSkill(UserskillsEntity userSkill) {
        userskillsRepository.save(userSkill);
        return userSkill;
    }

    @Override
    public boolean deleteUserSkill(UserskillsEntity userSkill) {
        userskillsRepository.delete(userSkill);
        return true;
    }

    @Override
    public UserskillsEntity updateUserSkills(UserskillsEntity userSkill) {
        userskillsRepository.save(userSkill);
        return userSkill;
    }
}
