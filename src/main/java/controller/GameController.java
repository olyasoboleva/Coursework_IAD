package controller;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.*;

import javax.json.Json;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/hungergames/game")
@EnableAutoConfiguration
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    TributeService tributeService;

    @Autowired
    UserService userService;

    @Autowired
    WeaponService weaponService;

    @Autowired
    WeaponsInGameService weaponsInGameService;

    @Autowired
    PresentsToTributeService presentsToTributeService;

    @Autowired
    ShopService shopService;

    @GetMapping( "/get_tributes_of_game")
    public @ResponseBody ResponseEntity getGameTributes(@RequestParam("game") String gameId) {
        Game game = gameService.getGameById(Integer.parseInt(gameId));
        List<Tribute> tributes = tributeService.getTributesByGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(tributes);
    }

    @GetMapping( "/get_player_info")
    public @ResponseBody ResponseEntity getPlayerInfo(@RequestParam("game") String gameId) {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = getTributeByUser(user, gameId);
        int[] info = new int[3];
        info[0] = tribute.getHealth();
        info[1] = tribute.getHunger();
        info[2] = tribute.getThirst();
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }

    @PostMapping("/drop_weapon")
    public @ResponseBody ResponseEntity dropWeapon(@RequestParam("game") String gameId, @RequestParam("weapon") String weapon) {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = getTributeByUser(user, gameId);
        List<WeaponsInGame> userWeapons = weaponsInGameService.getWeaponsInGameByTribute(tribute);
        Weapon weap = weaponService.getWeaponByName(weapon);
        WeaponsInGame userWeapon = null;
        for (WeaponsInGame e: userWeapons) {
            if (e.getWeapon().equals(weap)) {
                userWeapon = e;
            }
        }
        weaponsInGameService.deleteWeaponsInGame(userWeapon);
        return ResponseEntity.status(HttpStatus.OK).body("Weapon deleted");
    }

    @PostMapping("/add_weapon")
    public @ResponseBody ResponseEntity addWeapon(@RequestParam("game") String gameId, @RequestParam("weaponName") String weaponName) {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = getTributeByUser(user, gameId);
        List<WeaponsInGame> userWeapons = weaponsInGameService.getWeaponsInGameByTribute(tribute);
        if (userWeapons.size() >= 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can't take more weapons");
        }
        Weapon weapon = weaponService.getWeaponByName(weaponName);
        WeaponsInGame weaponsInGame = new WeaponsInGame(tribute,weapon);
        weaponsInGameService.createWeaponsInGame(weaponsInGame);
        return ResponseEntity.status(HttpStatus.OK).body("Weapon added");
    }

    @PostMapping("/drop_present")
    public @ResponseBody ResponseEntity dropPresent(@RequestParam("game") String gameId, @RequestParam("presentName") String presentName){
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = getTributeByUser(user, gameId);
        Shop product = shopService.getProductByName(presentName);
        PresentsToTribute presentToDrop = null;
        List<PresentsToTribute> presents = presentsToTributeService.getPresentsToTributeByTribute(tribute);
        for (PresentsToTribute e : presents) {
            if (e.getProduct().equals(product)) {
                presentToDrop = e;
            }
        }
        presentsToTributeService.deletePresentsToTributes(presentToDrop);
        return ResponseEntity.status(HttpStatus.OK).body("Present deleted");
    }

    @GetMapping("/tribute_info")
    public @ResponseBody ResponseEntity getTributeInfo(@RequestParam("nick")String nick) {
        User user = userService.getUserByNick(nick);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/games_history")
    public @ResponseBody ResponseEntity getGamesHistory() {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getAllGames());
    }

    @GetMapping("/games_by_date")
    public @ResponseBody ResponseEntity getGamesByDate(@RequestParam("date") java.util.Date date) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getGameByStartDate(date));
    }

    private Tribute getTributeByUser(User user, String gameId) {
        Game game = gameService.getGameById(Integer.parseInt(gameId));
        List<Tribute> tributes = tributeService.getTributesByGame(game);
        Tribute tribute = null;
        for (Tribute e : tributes) {
            if (user.equals(userService.getUserByTribute(e))) {
                tribute = e;
            }
        }
        return tribute;
    }

    //FIXME: надо хранить координаты по-другому
    @PostMapping("/move")
    public @ResponseBody ResponseEntity updateXY(@RequestParam("x") int newX, @RequestParam("y") int newY, @RequestParam("game") Integer gameID) {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Game game = gameService.getGameById(gameID);
        Tribute tribute = tributeService.getTributeByUserAndGame(user, game);
        tribute.setX(newX);
        tribute.setY(newY);
        return ResponseEntity.status(HttpStatus.OK).body(tribute);
    }
}