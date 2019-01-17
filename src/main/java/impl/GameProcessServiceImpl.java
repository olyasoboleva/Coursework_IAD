package impl;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.*;
import service.GameProcessService;
import service.UserService;
import service.WeaponService;

import java.util.Collection;
import java.util.List;

@Service("gameProcess")
public class GameProcessServiceImpl implements GameProcessService {
    private final TributeRepository tributeRepository;
    private final UserService userService;
    private final StatusRepository statusRepository;
    private final WeaponService weaponService;
    private final GameRepository gameRepository;
    private final PriceRepository priceRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;
    private final PresentsToTributeRepository presentsToTributeRepository;

    @Autowired
    public GameProcessServiceImpl(TributeRepository tributeRepository, UserService userService, StatusRepository statusRepository, WeaponService weaponService, GameRepository gameRepository, PriceRepository priceRepository, SkillRepository skillRepository, UserSkillRepository userSkillRepository, PresentsToTributeRepository presentsToTributeRepository) {
        this.tributeRepository = tributeRepository;
        this.userService = userService;
        this.statusRepository = statusRepository;
        this.weaponService = weaponService;
        this.gameRepository = gameRepository;
        this.priceRepository = priceRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
        this.presentsToTributeRepository = presentsToTributeRepository;
    }

    public void changeStatusAfterEndOfTheGame(Game game, Tribute winner) {
        List<Tribute> allTributes = tributeRepository.getTributesByGame(game);
        int sponsorProfit = priceRepository.findPriceByName("winner's sponsor").getCost();
        if (winner!=null) {
            winner.setStatus("Победитель");
            winner.getUser().setCash(winner.getUser().getCash() + priceRepository.findPriceByName("winner").getCost());
            tributeRepository.save(winner);
            Collection<User> sponsors = winner.getPresentsSenders();
            for (User sponsor: sponsors){
                sponsor.setCash(sponsor.getCash()+sponsorProfit);
            }
        }
        game.setStatus("game over");
        gameRepository.save(game);
        Status status = statusRepository.findStatuseByName("Наблюдатель");
        for (Tribute tribute: allTributes) {
            User user = userService.getUserByTribute(tribute);
            user.setStatus(status);
            userService.removeRole(user, "ROLE_TRIBUTE");
            userService.updateUser(user);
        }
    }


    @Override
    public void fight(Tribute attacking, Tribute defending, String attWeaponName, String defWeaponName) {
        Weapon attWeapon = weaponService.getWeaponByName(attWeaponName);
        Weapon defWeapon = weaponService.getWeaponByName(defWeaponName);
        Skill attSkill, defSkill;
        attSkill = tributeWithoutWeapon(attWeapon);
        defSkill = tributeWithoutWeapon(defWeapon);
        UserSkill attSkillLevel = userSkillRepository.findUserSkillByUserAndSkill(attacking.getUser(), attSkill);
        UserSkill defSkillLevel = userSkillRepository.findUserSkillByUserAndSkill(defending.getUser(), defSkill);
        int distance = Math.max(Math.abs(attacking.getLocationX()-defending.getLocationX()), Math.abs(attacking.getLocationY()-defending.getLocationY()));
        int attProtect = weaponService.getProtectionOftribute(attacking);
        int defProtect = weaponService.getProtectionOftribute(defending);
        int damage;
        for (int i=0; i<3 && attacking.getHealth()>=0 && defending.getHealth()>=0; i++){
            if (distance<=attWeapon.getRadiusOfAction()) {
                damage = (int) ((100 + attSkillLevel.getLevelOfSkill()) * attWeapon.getDamage() * (100 - defProtect) * (Math.random() * 5 + 1) / 10000);
                defending.setHealth(defending.getHealth() - damage);
            }
            if (distance<=defWeapon.getRadiusOfAction()) {
                damage = (int) ((100 + defSkillLevel.getLevelOfSkill()) * defWeapon.getDamage() * (100 - attProtect) * (Math.random() * 5 + 1) / 10000);
                attacking.setHealth(attacking.getHealth() - damage);
            }
        }
        attSkillLevel.setLevelOfSkill(attSkillLevel.getLevelOfSkill()+1);
        defSkillLevel.setLevelOfSkill(defSkillLevel.getLevelOfSkill()+1);
        userSkillRepository.save(attSkillLevel);
        userSkillRepository.save(defSkillLevel);
        tributeRepository.save(attacking);
        tributeRepository.save(defending);
    }

    private Skill tributeWithoutWeapon(Weapon weapon){
        Skill skill;
        if (weapon==null){
            weapon = new Weapon(); weapon.setDamage(5);
            weapon.setRadiusOfAction(1);
            skill = skillRepository.findSkillByName("Рукопашный бой");
        } else {
            skill = skillRepository.findSkillByWeapon(weapon);
        }
        return skill;
    }

}
