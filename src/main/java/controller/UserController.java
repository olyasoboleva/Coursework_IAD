package controller;

import entity.Skill;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.SkillService;
import service.UserLoginService;
import service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class UserController {

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    SkillService skillService;

    @GetMapping( "/personal_page")
    public @ResponseBody ResponseEntity getUserInfo() {
        User user = userLoginService.getUserLoginByNick( SecurityContextHolder.getContext().getAuthentication().getName()).getUser();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping( "/get_all_skills")
    public @ResponseBody ResponseEntity getUserSkills() {
        User user = userLoginService.getUserLoginByNick( SecurityContextHolder.getContext().getAuthentication().getName()).getUser();
        List<Skill> skills = skillService.getAllUserSkills(user);
        return ResponseEntity.status(HttpStatus.OK).body(skills);
    }

}