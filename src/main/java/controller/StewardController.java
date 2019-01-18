package controller;

import entity.Arena;
import entity.Game;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

   /* @Secured("ROLE_ADMIN")
    @PostMapping( "/create_arena")
    public @ResponseBody ResponseEntity createArena(int length, int width, String locationName) {
        Arena arena = new Arena(length, width, locationService.findLocationByName(locationName));
        arenaService.createArena(arena);
        return ResponseEntity.status(HttpStatus.OK).body(arena);
    }
*/
    @Secured("ROLE_ADMIN")
    @PostMapping( "/create_game")
    public @ResponseBody ResponseEntity createGame(boolean typeOfGame, int length, int width, String locationName, String date) {
        entity.User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Arena arena = new Arena(length, width, locationService.findLocationByName(locationName));
        arenaService.createArena(arena);
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(new Date(date));
            int numberOfTributes;
            if (typeOfGame){
                numberOfTributes = 24;
            } else {
                numberOfTributes = 48;
            }
            Game game = new Game(typeOfGame, user, arena, numberOfTributes, startDate);
            if (gameService.createGame(game)==null){
                return ResponseEntity.status(HttpStatus.OK).body("Этот день для проведения игр уже занят.");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(game);
            }

    }

    @Secured("ROLE_ADMIN")
    @PostMapping( "/add_role")
    public @ResponseBody ResponseEntity addRoleToUser(String nick, String role) {
        entity.User user = userService.getUserByNick(nick);
        userService.addRole(user, role);
        return ResponseEntity.status(HttpStatus.OK).body("Роль добавлена");
    }

    @Secured("ROLE_ADMIN")
    @PostMapping( "/del_role")
    public @ResponseBody ResponseEntity removeRoleFromUser(String nick, String role) {
        entity.User user = userService.getUserByNick(nick);
        userService.removeRole(user, role);
        return ResponseEntity.status(HttpStatus.OK).body("Роль удалена");
    }
}