package impl;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.*;
import service.TributeService;

import java.util.List;


@Service("tributeService")
public class TributeServiceImpl implements TributeService {

    private final TributeRepository tributeRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final MapRepository mapRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;


    @Autowired
    public TributeServiceImpl(TributeRepository tributeRepository, UserRepository userRepository, StatusRepository statusRepository, MapRepository mapRepository, SkillRepository skillRepository, UserSkillRepository userSkillRepository) {
        this.tributeRepository = tributeRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.mapRepository = mapRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
    }

    /**
     * It creates tribute after checking his age
     * @param tribute tribute
     * @return tribute if age is correct and null if incorrect
     */
    @Transactional
    @Override
    public Tribute createTribute(Tribute tribute) {
        tributeRepository.save(tribute);
        User user = userRepository.findUserByTributesByUser(tribute);
        Status status = statusRepository.findStatuseByName("tribute");
        user.setStatus(status);
        userRepository.save(user);
        return tribute;
    }

    @Transactional
    @Override
    public boolean deleteTribute(Tribute tribute) {
        tributeRepository.delete(tribute);
        return true;
    }

    @Transactional
    @Override
    public Tribute updateTribute(Tribute tribute) {
        return tributeRepository.save(tribute);
    }

    @Override
    public Tribute getTributeById(int tributeId) {
        return tributeRepository.findTributeByTributeId(tributeId);
    }

    @Override
    public List<Tribute> getTributesByUser(User user) {
        return tributeRepository.getTributesByUser(user);
    }

    @Override
    public List<Tribute> getTributesByGame(Game game) {
        return tributeRepository.getTributesByGame(game);
    }

    @Override
    public List<Tribute> getTributesByStatusAndGame(String status, Game game) {
        return tributeRepository.getTributesByStatusAndGame(status, game);
    }

    @Override
    public Tribute getTributeByUserAndGame(User user, Game game){
        return tributeRepository.getTributeByUserAndGame(user, game);
    }

    @Override
    public List<Tribute> getTributeInArea(Game game, int x, int y, int radius) {
        return tributeRepository.getTributesByGameAndLocationXBetweenAndLocationYIsBetween(game, x-radius,x+radius,y-radius,y+radius);
    }

    @Override
    public void moveTribute(Tribute tribute, int newX, int newY) {
        tribute.setLocationX(newX);
        tribute.setLocationY(newY);

        tributeRepository.save(tribute);
        /*int koef = 2;
        Skill necessarySkill = null;
        String curLocation = mapRepository.findMapByArenaAndXCoordinateAndYCoordinate(tribute.getGame().getArena(), newX, newY).getLocation().getName();
        switch (curLocation){
            case "Горы": necessarySkill = skillRepository.findSkillByName("Скалолазание");
                break;
            case "Водная": necessarySkill = skillRepository.findSkillByName("Плавание");
                break;
        }
        if (necessarySkill!=null){
            if (!tribute.getUser().getSkills().contains(necessarySkill)){
                tribute.setHealth(tribute.getHealth() - koef);
            } else {
                tribute.setHealth(tribute.getHealth()-koef*(100-userSkillRepository.findUserSkillByUserAndSkill(tribute.getUser(), necessarySkill).getLevelOfSkill()));
            }
        }
        if (tribute.getHealth()<=0){
            tribute.setStatus("dead");
        }*/
    }
}
