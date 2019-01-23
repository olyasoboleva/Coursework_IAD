package controller;

import entity.*;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/hungergames/game")
@EnableAutoConfiguration
public class GameController {

    private final GameService gameService;
    private final TributeService tributeService;
    private final UserService userService;
    private final WeaponService weaponService;
    private final WeaponsInGameService weaponsInGameService;
    private final PresentsToTributeService presentsToTributeService;
    private final ShopService shopService;
    private final ProductsAndLocationService productsAndLocationService;
    private final WebSocketController webSocketController;
    private final MapService mapService;
    private final LocationService locationService;

    @Autowired
    public GameController(GameService gameService, TributeService tributeService, UserService userService, WeaponService weaponService, WeaponsInGameService weaponsInGameService, PresentsToTributeService presentsToTributeService, ShopService shopService, ProductsAndLocationService productsAndLocationService, WebSocketController webSocketController, MapService mapService, LocationService locationService) {
        this.gameService = gameService;
        this.tributeService = tributeService;
        this.userService = userService;
        this.weaponService = weaponService;
        this.weaponsInGameService = weaponsInGameService;
        this.presentsToTributeService = presentsToTributeService;
        this.shopService = shopService;
        this.productsAndLocationService = productsAndLocationService;
        this.webSocketController = webSocketController;
        this.mapService = mapService;
        this.locationService = locationService;
    }

    @Secured("ROLE_USER")
    @GetMapping( "/get_tributes_of_game")
    public @ResponseBody ResponseEntity getGameTributes(@RequestParam("game") String gameId) {
        Game game = gameService.getGameById(Integer.parseInt(gameId));
        List<Tribute> tributes = tributeService.getTributesByGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(tributes);
    }

    @Secured("ROLE_TRIBUTE")
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
        userWeapons = weaponsInGameService.getWeaponsInGameByTribute(tribute);
        return ResponseEntity.status(HttpStatus.OK).body(userWeapons);
    }

    @Secured("ROLE_TRIBUTE")
    @PostMapping("/add_weapon")
    public @ResponseBody ResponseEntity addWeapon(@RequestParam("game") String gameId, @RequestParam("weapon") String weaponName) {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = getTributeByUser(user, gameId);
        List<WeaponsInGame> userWeapons = weaponsInGameService.getWeaponsInGameByTribute(tribute);
        if (userWeapons.size() >= 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can't take more weapons");
        }
        Weapon weapon = weaponService.getWeaponByName(weaponName);
        WeaponsInGame weaponsInGame = new WeaponsInGame(tribute,weapon);
        weaponsInGameService.createWeaponsInGame(weaponsInGame);
        userWeapons = weaponsInGameService.getWeaponsInGameByTribute(tribute);
        return ResponseEntity.status(HttpStatus.OK).body(userWeapons);
    }

    @Secured("ROLE_TRIBUTE")
    @PostMapping("/drop_present")
    public @ResponseBody ResponseEntity dropPresent(@RequestParam("game") String gameId, @RequestParam("present") String presentName){
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
        presents = presentsToTributeService.getPresentsToTributeByTribute(tribute);
        return ResponseEntity.status(HttpStatus.OK).body(presents);
    }

    @Secured({"ROLE_USER","ROLE_TRIBUTE"})
    @GetMapping("/tribute_info")
    public @ResponseBody ResponseEntity getTributeInfo(@RequestParam("nick")String nick) {
        User user = userService.getUserByNick(nick);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Secured("ROLE_USER")
    @GetMapping("/games_history")
    public @ResponseBody ResponseEntity getGamesHistory() {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getAllGames());
    }

    @Secured("ROLE_USER")
    @GetMapping("/games_by_date")
    public @ResponseBody ResponseEntity getGamesByDate(@RequestParam("date") long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getGameByStartDate(calendar));
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

    @Secured("ROLE_TRIBUTE")
    @PostMapping("/use_present")
    public @ResponseBody ResponseEntity usePresent(String presentName){
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Game game = gameService.getGameByStartDate(Calendar.getInstance());
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
            if (!present.getTypeOfPresent().equals("Инструменты") && !present.getTypeOfPresent().equals("Другое")) {
                presentsToTribute.setQuantity(presentsToTribute.getQuantity() - 1);
                if (presentsToTribute.getQuantity() <= 0) {
                    return dropPresent(game.getGameId().toString(), presentName);
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

    @Secured("ROLE_TRIBUTE")
    @PostMapping("/move")
    public @ResponseBody ResponseEntity moveAndGetVisibleMap(TributeLocation tributeLocation){
        webSocketController.moveTribute(tributeLocation);
        return getVisibleWeapon(tributeLocation);
    }

    @Secured("ROLE_USER")
    @GetMapping("/game_start_pack")
    public @ResponseBody ResponseEntity getMap(){
        Game game  = gameService.getGameByStartDate(Calendar.getInstance());
        List<Location> locations = locationService.findAll();
        List<Map> area = mapService.getAllGameField(game.getArena());
        VisibleMap visibleMap  =new VisibleMap();
        visibleMap.setArea(area);
        visibleMap.setLocation(locations);
        return ResponseEntity.status(HttpStatus.OK).body(visibleMap);
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/send_present")
    public void sendPresent(String nick, int presentID, int quantity){
        User sender = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = tributeService.getTributeByUserAndGame(userService.getUserByNick(nick),gameService.getGameByStartDate(Calendar.getInstance()));
        Shop present = shopService.getProductById(presentID);
        webSocketController.sendPresent(sender, tribute, present, quantity);
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/get_map")
    public @ResponseBody ResponseEntity getVisibleWeapon(TributeLocation tributeLocation){
        int radius = 3;
        Game game = gameService.getGameByStartDate(Calendar.getInstance());
        List<WeaponsInGame> weapons = weaponsInGameService.getWeaponsInGameInAreaWithoutOwner(game, tributeLocation.getX(), tributeLocation.getY(), radius);
        return ResponseEntity.status(HttpStatus.OK).body(weapons);
    }

    @Secured({"ROLE_TRIBUTE"})
    @GetMapping("/get_tribute_presents")
    public @ResponseBody ResponseEntity getTributePresents(String gameId){
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = getTributeByUser(user, gameId);
        List<PresentsToTribute> presents = presentsToTributeService.getPresentsToTributeByTribute(tribute);
        return ResponseEntity.status(HttpStatus.OK).body(presents);
    }

    @Secured({"ROLE_TRIBUTE"})
    @GetMapping("/get_tribute_weapons")
    public @ResponseBody ResponseEntity getTributeWeapons(String gameId){
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = getTributeByUser(user, gameId);
        List<WeaponsInGame> weapons = weaponsInGameService.getWeaponsInGameByTribute(tribute);
        return ResponseEntity.status(HttpStatus.OK).body(weapons);
    }

    @Secured({"ROLE_TRIBUTE"})
    @GetMapping("/tribute")
    public @ResponseBody ResponseEntity getTribute(){
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Game game = gameService.getGameByStartDate(Calendar.getInstance());
        Tribute tribute = tributeService.getTributeByUserAndGame(user, game);
        return ResponseEntity.status(HttpStatus.OK).body(tribute);
    }

    @Secured({"ROLE_TRIBUTE"})
    @GetMapping("/health")
    public @ResponseBody ResponseEntity getHealth(){
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Game game = gameService.getGameByStartDate(Calendar.getInstance());
        Tribute tribute = tributeService.getTributeByUserAndGame(user, game);
        TributeHealth tributeHealth = new TributeHealth(user.getNick(),tribute.getHealth(), tribute.getHunger(), tribute.getThirst());
        return ResponseEntity.status(HttpStatus.OK).body(tributeHealth);
    }

    @Secured({"ROLE_TRIBUTE"})
    @PostMapping("/beat")
    public @ResponseBody ResponseEntity Beat(String tribute, String weapon){
        Battle battle = new Battle();
        battle.setAttacking(SecurityContextHolder.getContext().getAuthentication().getName());
        battle.setDefending(tribute);
        battle.setAttWeaponName(weapon);
        webSocketController.battle(battle);
        return ResponseEntity.status(HttpStatus.OK).body("Атака завершена");
    }
}