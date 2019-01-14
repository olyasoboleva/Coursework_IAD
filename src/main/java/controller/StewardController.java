package controller;

import entity.Arena;
import entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.util.Calendar;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class StewardController {

    @Autowired
    GameService gameService;

    @Autowired
    ArenaService arenaService;

    @Autowired
    LocationService locationService;

    @Autowired
    UserService userService;

    @Autowired
    MapService mapService;

    @Autowired
    WeaponsInGameService weaponsInGameService;

    @PostMapping( "/create_arena")
    public @ResponseBody ResponseEntity createArena(int length, int width, String locationName) {
        Arena arena = new Arena(length, width, locationService.findLocationByName(locationName));
        arenaService.createArena(arena);
        mapService.createAllGameField(arena);
        return ResponseEntity.status(HttpStatus.OK).body(arena);
    }

    @PostMapping( "/create_game")
    public @ResponseBody ResponseEntity createGame(boolean typeOfGame, int arenaID, int numberOfTributes, Calendar startDate) {
        entity.User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Arena arena = arenaService.getArenaById(arenaID);
        Game game = new Game(typeOfGame, user, arena, numberOfTributes, startDate);
        if (gameService.createGame(game)==null){
            return ResponseEntity.status(HttpStatus.OK).body("Этот день для проведения игр уже занят.");
        } else {
            weaponsInGameService.throwWeaponsOnArena(game);
            return ResponseEntity.status(HttpStatus.OK).body(game);
        }
    }
}