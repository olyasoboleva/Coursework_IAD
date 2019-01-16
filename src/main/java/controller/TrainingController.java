package controller;

import entity.Skill;
import entity.Training;
import entity.User;
import entity.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.SkillService;
import service.TrainingService;
import service.UserService;
import service.UserSkillService;


@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class TrainingController {

    @Autowired
    UserService userService;

    @Autowired
    SkillService skillService;

    @Autowired
    TrainingService trainingService;

    @Autowired
    UserSkillService userSkillService;

    @Secured("ROLE_USER")
    @PostMapping( "/improveSkill")
    public @ResponseBody
    ResponseEntity improveSkill(@RequestParam("name") String train, @RequestParam("percent") int percent) {
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Training training = trainingService.getTrainingByName(train);
        Skill skill = skillService.getSkillByTraining(training);
        UserSkill userSkill = userSkillService.getUserSkillByUserAndSkill(user, skill);
        int koeff = percent * training.getCoefficient()/100;
        if (koeff != 0) {
            if (userSkill == null) {
                userSkill = new UserSkill(user, skill, koeff);
                userSkillService.createUserSkill(userSkill);
            } else {
                if (userSkill.getLevelOfSkill() + koeff < 100) {
                    userSkill.setLevelOfSkill(userSkill.getLevelOfSkill() + koeff);
                } else {
                    userSkill.setLevelOfSkill(100);
                }
                userSkillService.updateUserSkills(userSkill);
            }
        }
        user.setCash(user.getCash() - training.getCost());
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Secured("ROLE_USER")
    @GetMapping( "/trainingByName")
    public @ResponseBody ResponseEntity getTrainingByName(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(trainingService.getTrainingByName(name));
    }

    @Secured("ROLE_USER")
    @GetMapping( "/trainings")
    public @ResponseBody ResponseEntity getTrainings(@RequestParam("day") int day) {
        return ResponseEntity.status(HttpStatus.OK).body(trainingService.getTrainingsByDayOfWeek(day));
    }
    }
