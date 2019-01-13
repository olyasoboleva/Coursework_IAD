package controller;

import entity.Skill;
import entity.User;
import entity.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import service.SkillService;
import service.UserService;
import service.UserSkillService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserSkillService userSkillService;

    @GetMapping( "/personal_page")
    public @ResponseBody ResponseEntity getUserInfo() {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        //userService.updateUserLastActivity(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping( "/get_all_skills")
    public @ResponseBody ResponseEntity getUserSkills() {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        List<UserSkill> skills = userSkillService.getUserSkillsByUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(skills);
    }

    @PostMapping("/edit_user")
    public @ResponseBody ResponseEntity editUser(@RequestParam("password") String password, @RequestParam("newPassword") String newPassword ) {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        if (!BCrypt.checkpw(password,user.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN ).body("Wrong password");
        }
        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}