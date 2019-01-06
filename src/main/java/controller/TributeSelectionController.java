package controller;

import entity.Game;
import entity.Tribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class TributeSelectionController {

    @Autowired
    TributeService tributeService;

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @Autowired
    DistrictService districtService;

    @Autowired
    StatusService statusService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping( "/tributeSelection")
    public @ResponseBody ResponseEntity selectTributes(int gameID) {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        Game game = gameService.getGameById(gameID);
        Tribute tribute;
        List<entity.User> usersOnline = new ArrayList<>();
        List<entity.User> temp;
        List<entity.User> tributes = new ArrayList<>();
        Calendar greaterThan=Calendar.getInstance(),lessThan=Calendar.getInstance();
        greaterThan.add(Calendar.YEAR,-18);
        lessThan.add(Calendar.YEAR,-12);

        for(final Object principal : allPrincipals) {
            if(principal instanceof User) {
                usersOnline.add(userService.getUserByNick(((User)principal).getUsername()));
            }
        }
        for (int i=0;i<12;i++){
            for (int j=0;j<2;j++){
                temp = userService.getUsersForGame(districtService.getDistrictById(i+1),j==0, greaterThan, lessThan, statusService.getStatuseByName("viewer"));
                temp.retainAll(usersOnline);
                if (temp.size()!=0) {
                    tributes.add(temp.get((int)(Math.random()*temp.size())));
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body("Недостаточное число трибутов");
                }
            }
        }
        for (entity.User user: tributes){
            tribute = new Tribute(user, game);
            tributeService.createTribute(tribute);
        }

        return ResponseEntity.status(HttpStatus.OK).body(tributes);
    }
}