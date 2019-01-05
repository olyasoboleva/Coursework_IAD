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
import service.ArenaService;
import service.GameService;
import service.LocationService;
import service.UserService;

import java.sql.Date;
import java.util.ArrayList;
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

    @Autowired
    private SessionRegistry sessionRegistry;

    @PostMapping( "/create_arena")
    public @ResponseBody ResponseEntity createArena(int length, int width, String locationName) {
        Arena arena = new Arena(length, width, locationService.findLocationByName(locationName));
        arenaService.createArena(arena);
        return ResponseEntity.status(HttpStatus.OK).body(arena);
    }


    //FIXME: в таблице не сохраняется распорядитель
    @PostMapping( "/create_game")
    public @ResponseBody ResponseEntity createGame(boolean typeOfGame, int arenaID, int numberOfTributes, Date startDate) {
        entity.User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Arena arena = arenaService.getArenaById(arenaID);
        Game game = new Game(typeOfGame, user, arena, numberOfTributes, startDate);
        //select tributes method
        gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    //test
    @GetMapping( "/onlineUser")
    public @ResponseBody ResponseEntity getUsersOnline() {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        List<entity.User> usersOnline = new ArrayList<>();

        for(final Object principal : allPrincipals) {
            if(principal instanceof User) {
                usersOnline.add(userService.getUserByNick(((User)principal).getUsername()));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(usersOnline);
    }
}