package logging;

import entity.Training;
import entity.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import service.TrainingService;
import service.UserService;

@Aspect
public class TrainingLogger {

    @Autowired
    UserService userService;

    @Autowired
    TrainingService trainingService;

    /**
     * Log message for trainings
     * @param joinPoint join point
     */
    @After("execution(* controller.TrainingController.improveSkill(..)) && within(controller.TrainingController)")
    public void improveSkillChecker(JoinPoint joinPoint){
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        Object params  = joinPoint.getArgs()[0];
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Training training = trainingService.getTrainingByName(params.toString().trim());
        logger.info("Пользователь: " + user.getNick() +", Тренировка: " + params.toString() + ", Цена: " + training.getCost());
    }

    @AfterThrowing(value = "execution(* controller.TrainingController.improveSkill(..)) && within(controller.TrainingController)", throwing = "exc")
    public void catchImproveSkillException(JoinPoint joinPoint, Throwable exc) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        logger.error("Ошибка при оплате тренировке пользователем " + user.getNick() + ". Ошибка: " + exc.getClass().getSimpleName());
    }
}
