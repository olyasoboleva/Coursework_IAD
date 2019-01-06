package controller;

import entity.Skill;
import entity.Training;
import entity.User;
import entity.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.SkillService;
import service.TrainingService;
import service.UserLoginService;
import service.UserSkillService;


@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class TrainingController {

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    SkillService skillService;

    @Autowired
    TrainingService trainingService;

    @Autowired
    UserSkillService userSkillService;

    @PostMapping( "/improveSkill")
    public @ResponseBody
    ResponseEntity improveSkill(@RequestParam("name") String train, @RequestParam("procent") String procent) {
        User user = userLoginService.getUserLoginByNick(SecurityContextHolder.getContext().getAuthentication().getName()).getUser();
        Training training = trainingService.getTrainingByName(train);
        Skill skill = skillService.getSkillByTraining(training);
        UserSkill userSkill = userSkillService.getUserSkillByUserAndSkill(user, skill);
        int koeff = Integer.parseInt(procent)*training.getCoefficient();
        if (userSkill.getLevelOfSkill()+ koeff < 100) {
            userSkill.setLevelOfSkill(userSkill.getLevelOfSkill() + koeff);
        } else {
            userSkill.setLevelOfSkill(100);
        }
        userSkillService.updateUserSkills(userSkill);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping( "/trainings")
    public @ResponseBody ResponseEntity getTrainings(@RequestParam("day")int day) {
        return ResponseEntity.status(HttpStatus.OK).body(trainingService.getTrainingsByDayOfWeek(day));
    }
    }
