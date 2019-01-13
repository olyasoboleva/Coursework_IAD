package controller;

import entity.Arena;
import entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @PostMapping( "/create_arena")
    public @ResponseBody ResponseEntity createArena(int length, int width, String locationName) {
        Arena arena = new Arena(length, width, locationService.findLocationByName(locationName));
        arenaService.createArena(arena);
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
            return ResponseEntity.status(HttpStatus.OK).body(game);
        }
    }

    //TODO: create map
}