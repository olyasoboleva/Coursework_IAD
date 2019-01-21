package impl;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.*;
import service.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Service("gameProcess")
public class GameProcessServiceImpl implements GameProcessService {
    private final TributeService tributeService;
    private final UserService userService;
    private final StatusService statusService;
    private final WeaponService weaponService;
    private final GameRepository gameRepository;
    private final PriceRepository priceRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;
    private final DistrictService districtService;

    @Autowired
    public GameProcessServiceImpl(TributeService tributeService, UserService userService, StatusService statusService, WeaponService weaponService, GameRepository gameRepository, PriceRepository priceRepository, SkillRepository skillRepository, UserSkillRepository userSkillRepository, DistrictService districtService) {
        this.tributeService = tributeService;
        this.userService = userService;
        this.statusService = statusService;
        this.weaponService = weaponService;
        this.gameRepository = gameRepository;
        this.priceRepository = priceRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
        this.districtService = districtService;
    }

    public void changeStatusAfterEndOfTheGame(Game game, Tribute winner) {
        List<Tribute> allTributes = tributeService.getTributesByGame(game);
        User steward = userService.getSteward(game);
        int sponsorProfit = priceRepository.findPriceByName("winner's sponsor").getCost();
        if (winner!=null) {
            winner.setStatus("Победитель");
            winner.getUser().setCash(winner.getUser().getCash() + priceRepository.findPriceByName("winner").getCost());
            //userService.updateUser(winner.getUser());
            tributeService.updateTribute(winner);
            Collection<User> sponsors = winner.getPresentsSenders();
            for (User sponsor: sponsors){
                sponsor.setCash(sponsor.getCash()+sponsorProfit);
                userService.updateUser(sponsor);
            }
        }
        steward.setCash(steward.getCash() + priceRepository.findPriceByName("steward").getCost());
        userService.updateUser(steward);
        game.setStatus("game over");
        gameRepository.save(game);
        Status status = statusService.getStatuseByName("Наблюдатель");
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
        tributeService.updateTribute(attacking);
        tributeService.updateTribute(defending);
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

    @Override
    public List<User> selection(List<User> usersOnline, Game game){
        Calendar greaterThan=Calendar.getInstance(),lessThan=Calendar.getInstance();
        greaterThan.add(Calendar.YEAR,-18);
        lessThan.add(Calendar.YEAR,-12);
        Tribute tribute;
        List<entity.User> temp;
        List<entity.User> tributes = new ArrayList<>();
        Status tributeStatus = statusService.getStatuseByName("Трибут");
        Status viewerStatus = statusService.getStatuseByName("Наблюдатель");

        for (int doubleSelection=0;doubleSelection<=game.getNumberOfTributes()/48;doubleSelection++) {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 2; j++) {
                    temp = userService.getUsersForGame(districtService.getDistrictById(i + 1), j != 0, greaterThan, lessThan, viewerStatus);
                    temp.retainAll(usersOnline);
                    if (temp.size() != 0) {
                        tributes.add(temp.get((int) (Math.random() * temp.size())));
                        tributes.get(tributes.size()-1).setStatus(tributeStatus);
                    } else {
                        for (entity.User user: tributes){
                            tributes.get(tributes.size()-1).setStatus(viewerStatus);
                        }
                        return tributes;
                    }
                }
            }
        }
        for (entity.User user: tributes){
            tribute = new Tribute(user, game);
            userService.addRole(user, "ROLE_TRIBUTE");
            tributeService.createTribute(tribute);
        }

        return tributes;
    }
}
