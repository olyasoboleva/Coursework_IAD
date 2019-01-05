package controller;

import entity.Game;
import entity.Tribute;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.GameService;
import service.TributeService;
import service.UserLoginService;
import service.UserService;

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
    UserLoginService userLoginService;

    @Autowired
    UserService userService;

    @GetMapping( "/get_tributes_of_game")
    public @ResponseBody ResponseEntity getGameTributes(@RequestParam("game") String gameId) {
        Game game = gameService.getGameById(Integer.parseInt(gameId));
        List<Tribute> tributes = tributeService.getTributesByGame(game);
        return ResponseEntity.status(HttpStatus.OK).body(tributes);
    }

    @GetMapping( "/get_player_info")
    public @ResponseBody ResponseEntity getPlayerInfo(@RequestParam("game") String gameId) {
        User user = userLoginService.getUserLoginByNick(SecurityContextHolder.getContext().getAuthentication().getName()).getUser();
        Game game = gameService.getGameById(Integer.parseInt(gameId));
        List<Tribute> tributes = tributeService.getTributesByGame(game);
        Tribute tribute = null;
        for (Tribute e : tributes) {
            if (user.equals(userService.getUserByTribute(e))) {
                tribute = e;
            }
        }
        int[] info = new int[3];
        info[0] = tribute.getHealth();
        info[1] = tribute.getHunger();
        info[2] = tribute.getThirst();
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }

}