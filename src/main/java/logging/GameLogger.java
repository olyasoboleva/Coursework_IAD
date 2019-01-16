package logging;

import entity.*;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import service.*;


@Aspect
public class GameLogger {

    @Autowired
    UserService userService;

    @Autowired
    TrainingService trainingService;

    @Autowired
    TributeService tributeService;

    @Autowired
    ShopService shopService;

    @Autowired
    StatusService statusService;


    @After("execution(* controller.TrainingController.improveSkill(..)) && within(controller.TrainingController)")
    public void improveSkillChecker(JoinPoint joinPoint){
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        Object params  = joinPoint.getArgs()[0];
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Training training = trainingService.getTrainingByName(params.toString().trim());
        logger.info("Пользователь: " + user.getNick() +", Тренировка: " + params.toString() + ", Цена: " + training.getCost());
    }

    @After("execution(* controller.WebSocketController.sendPresent(..)) && within(controller.WebSocketController)")
    public void sendPresentLogger(JoinPoint joinPoint) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        Object[] params  = joinPoint.getArgs();
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = tributeService.getTributeById((Long) params[0]);
        Shop product = shopService.getProductById((Integer) params[1]);
        logger.info("Отправитель: " + user.getNick() + ", Трибут: " + tribute.getUser().getNick() +", Количество: " + params[2] + ", Цена: " + product.getCost());

    }

    @After("execution(* impl.UserServiceImpl.updateUserLastActivity(..)) && within(impl.UserServiceImpl)")
    public void sendBonuce(JoinPoint joinPoint) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        int price = statusService.getStatuseByName("Daily prize").getPrice().getCost();
        logger.info("Пользователь: " + user.getNick() + "Бонус: " + price + "Баланс: " + user.getCash());
    }
}
