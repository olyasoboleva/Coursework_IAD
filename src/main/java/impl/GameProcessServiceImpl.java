package impl;

import controller.WebSocketController;
import entity.*;
import model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private final UserSkillService userSkillService;
    private final DistrictService districtService;
    private  final WebSocketController webSocketController;

    @Autowired
    public GameProcessServiceImpl(TributeService tributeService, UserService userService, StatusService statusService, WeaponService weaponService, GameRepository gameRepository, PriceRepository priceRepository, SkillRepository skillRepository, UserSkillService userSkillService, DistrictService districtService, WebSocketController webSocketController) {
        this.tributeService = tributeService;
        this.userService = userService;
        this.statusService = statusService;
        this.weaponService = weaponService;
        this.gameRepository = gameRepository;
        this.priceRepository = priceRepository;
        this.skillRepository = skillRepository;
        this.userSkillService = userSkillService;
        this.districtService = districtService;
        this.webSocketController = webSocketController;
    }

    public void changeStatusAfterEndOfTheGame(Game game, Tribute winner) {
        List<Tribute> allTributes = tributeService.getTributesByGame(game);
        User steward = userService.getSteward(game);
        int sponsorProfit = priceRepository.findPriceByName("winner's sponsor").getCost();
        if (winner!=null) {
            winner.setStatus("Победитель");
            winner.getUser().setCash(winner.getUser().getCash() + priceRepository.findPriceByName("winner").getCost());
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
        game.setDuration((int)((Calendar.getInstance().getTimeInMillis()-game.getStartDate().getTimeInMillis())/1000/60));
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
    public void fight(Tribute attacking, Tribute defending, Weapon weapon) {
        Weapon attWeapon = weapon;
        Skill attSkill;
        if (attWeapon==null){
            attWeapon = new Weapon(); attWeapon.setDamage(5);
            attWeapon.setRadiusOfAction(1);
            attSkill = skillRepository.findSkillByName("Рукопашный бой");
        } else {
            attSkill = skillRepository.findSkillByWeapon(weapon);
        }
        UserSkill attSkillLevel = userSkillService.getUserSkillByUserAndSkill(attacking.getUser(), attSkill);
        int distance = Math.max(Math.abs(attacking.getLocationX()-defending.getLocationX()), Math.abs(attacking.getLocationY()-defending.getLocationY()));
        int defProtect = weaponService.getProtectionOftribute(defending);
        int damage, level=0;
        if (attSkillLevel!=null){
            level = attSkillLevel.getLevelOfSkill();
        }
        if (distance<=attWeapon.getRadiusOfAction()) {
            damage = (int) ((100 + level) * attWeapon.getDamage() * (100 - defProtect) * (Math.random() * 2) / 100000);
            damage = tributeService.getDamage(defending, damage);
            webSocketController.userGameEvent(new Message(attacking.getUser().getNick()+" нанес вам урон "+damage,defending.getUser().getNick(), Message.Type.ATTACK));
            webSocketController.userGameEvent(new Message("Вы нанесли "+defending.getUser().getNick()+" урон "+damage,attacking.getUser().getNick(), Message.Type.SUCCESSATTACK));
            userSkillService.incLevel(attSkill, attacking.getUser());
            tributeService.updateTribute(attacking);
            tributeService.updateTribute(defending);
        }
    }

    @Override
    public List<User> selection(List<User> usersOnline, Game game){
        Calendar greaterThan=Calendar.getInstance(),lessThan=Calendar.getInstance();
        greaterThan.add(Calendar.YEAR,-18);
        lessThan.add(Calendar.YEAR,-12);
        Tribute tribute;
        List<entity.User> temp;
        List<entity.User> tributes = new ArrayList<>();
        List<String> userOnlineNick = new ArrayList<>();
        List<String> tempNick = new ArrayList<>();
        Status tributeStatus = statusService.getStatuseByName("Трибут");
        Status viewerStatus = statusService.getStatuseByName("Наблюдатель");

        for (User user: usersOnline){
            userOnlineNick.add(user.getNick());
        }

        for (int doubleSelection=0;doubleSelection<=game.getNumberOfTributes()/48;doubleSelection++) {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 2; j++) {
                    temp = userService.getUsersForGame(districtService.getDistrictById(i + 1), j != 0, greaterThan, lessThan, viewerStatus);
                    for (User user: temp){
                        tempNick.add(user.getNick());
                    }
                    tempNick.retainAll(userOnlineNick);
                    if (temp.size() != 0) {
                        tributes.add(userService.getUserByNick(tempNick.get((int) (Math.random() * temp.size()))));
                        tributes.get(tributes.size()-1).setStatus(tributeStatus);
                    }
                }
            }
        }
        game.setNumberOfTributes(tributes.size());
        gameRepository.save(game);
        String msg = "В результате прошедшей жатвы вы выбраны трибутом "+game.getGameId()+" Голодных игр, которые пройдут через "+ (int)((Calendar.getInstance().getTimeInMillis()-game.getStartDate().getTimeInMillis())/1000/60)+" дней";
        for (entity.User user: tributes){
            tribute = new Tribute(user, game);
            tribute.setLocationX((int)(Math.random()*(game.getArena().getArenaLength()-3))+4);
            tribute.setLocationY((int)(Math.random()*(game.getArena().getArenaWidth())-3)+4);
            webSocketController.userGameEvent(new Message(msg, user.getNick(), Message.Type.SELECTION));
            userService.addRole(user, "ROLE_TRIBUTE");
            tributeService.createTribute(tribute);
        }

        return tributes;
    }

    @Override
    public boolean isGameOver(Game game){
        List<Tribute> tributesAlive = tributeService.getTributesByStatusAndGame("alive", game);
        if (tributesAlive.size() == 1){
            changeStatusAfterEndOfTheGame(game, tributesAlive.get(0));
            webSocketController.gameEvent(new Message("Конец игры! Победитель - " + tributesAlive.get(0).getUser().getNick(),"", Message.Type.GAMEOVER));
            return true;
        } else {
            if (tributesAlive.size() == 0) {
                changeStatusAfterEndOfTheGame(game, null);
                webSocketController.gameEvent(new Message("Конец игры! Но все умерли:)", "", Message.Type.GAMEOVER));
                return true;
            }
        }
        return false;
    }
}
