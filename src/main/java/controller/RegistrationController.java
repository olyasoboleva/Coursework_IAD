package controller;

import entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import service.UserLoginService;

@RestController
@RequestMapping("/hungergame")
@EnableAutoConfiguration
public class RegistrationController {

    @Autowired
    UserLoginService userLoginService;

    @PostMapping( "/signup")
    public @ResponseBody ResponseEntity registerUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserLogin user = new UserLogin(username, BCrypt.hashpw(password, BCrypt.gensalt()));
        if ((username.equals("")) || (password.equals(""))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect username or password");
        }
        UserLogin someUser = userLoginService.getUserLoginByNick(username);
        if (someUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
        userLoginService.createLogin(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}