package controller;

import entity.Status;
import entity.User;
import entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import service.StatusService;
import service.UserLoginService;
import service.UserService;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class RegistrationController {

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    UserService userService;

    @Autowired
    StatusService statusService;

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

    @PostMapping( "/new_user")
    public @ResponseBody ResponseEntity createUser(String surname, String name, byte[] picture) {
        UserLogin userLogin = userLoginService.getUserLoginByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        User user = new User(surname, name, picture, userLogin, statusService.getStatuseById(1));
        user.setHeight(140);
        user.setWeight(45);
        userService.createUser(user);
        userLogin.setUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(userLogin);
    }
}