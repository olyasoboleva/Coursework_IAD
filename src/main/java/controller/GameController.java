package controller;

import entity.*;
import model.TributeHealth;
import model.TributeLocation;
import model.VisibleMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

    //TODO: надо бы распределить по другим контроллерам (много зависимостей)

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

    @Autowired
    ProductsAndLocationService productsAndLocationService;

    @Autowired
    WebSocketController webSocketController;

    @Autowired
    MapService mapService;

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
        if (userWeapon!=null) {
            userWeapon.setLocationX(tribute.getLocationX());
            userWeapon.setLocationY(tribute.getLocationY());
            userWeapon.setTribute(null);
            weaponsInGameService.updateWeaponsInGame(userWeapon);
        }
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
    public @ResponseBody ResponseEntity getGamesByDate(@RequestParam("date") Calendar date) {
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

    @PostMapping("/use_present")
    public @ResponseBody ResponseEntity usePresent(Integer gameId, String presentName){
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Game game = gameService.getGameById(gameId);
        Tribute tribute = tributeService.getTributeByUserAndGame(user, game);
        Location location = mapService.getCell(game.getArena(), tribute.getLocationX(), tribute.getLocationY()).getLocation();
        Shop present = shopService.getProductByName(presentName);
        if (productsAndLocationService.getApplying(present, location)!=null){
            PresentsToTribute presentsToTribute = presentsToTributeService.getPresentByProductAndTribute(present, tribute);
            switch (present.getTypeOfRecovery()){
                case "Еда":
                    tribute.setHunger(tribute.getHunger()+present.getHealthRecovery()>100?100:tribute.getHunger()+present.getHealthRecovery());
                    break;
                case "Лекарство":
                    tribute.setHealth(tribute.getHealth()+present.getHealthRecovery()>100?100:tribute.getHealth()+present.getHealthRecovery());
                    break;
                case "Напиток":
                    tribute.setThirst(tribute.getThirst()+present.getHealthRecovery()>100?100:tribute.getThirst()+present.getHealthRecovery());
            }
            tributeService.updateTribute(tribute);
            webSocketController.getHealth(new TributeHealth(user.getNick(), tribute.getHealth(), tribute.getHunger(), tribute.getThirst()));
            if (!present.getTypeOfPresent().equals("Инструменты")) {
                presentsToTribute.setQuantity(presentsToTribute.getQuantity() - 1);
                if (presentsToTribute.getQuantity() <= 0) {
                    return dropPresent(gameId.toString(), presentName);
                } else {
                    presentsToTributeService.updatePresentsToTributes(presentsToTribute);
                    return ResponseEntity.status(HttpStatus.OK).body(presentsToTribute);
                }
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(presentsToTribute);
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Нельзя применить подарок в текущей локации");
        }
    }

    @GetMapping("/move")
    public @ResponseBody ResponseEntity getVisibleMap(TributeLocation tributeLocation){
        webSocketController.moveTribute(tributeLocation);
        int radius = 4;
        Game game = gameService.getGameById(tributeLocation.getGameId());
        List<Map> area = mapService.getArea(game.getArena(), tributeLocation.getX(), tributeLocation.getY());
        List<WeaponsInGame> weapons = weaponsInGameService.getWeaponsInGameInAreaWithoutOwner(game, tributeLocation.getX(), tributeLocation.getY(), radius);
        VisibleMap visibleMap = new VisibleMap();
        visibleMap.setArea(area);
        visibleMap.setWeapons(weapons);
        return ResponseEntity.status(HttpStatus.OK).body(visibleMap);
    }
}