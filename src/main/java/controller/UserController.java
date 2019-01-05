package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping( "/personal_page")
    public @ResponseBody ResponseEntity getUserInfo() {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        userService.updateUserLastActivity(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}