package logging;

import entity.Shop;
import entity.Tribute;
import entity.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import service.GameService;
import service.ShopService;
import service.TributeService;
import service.UserService;

import java.util.Calendar;

@Aspect
public class ShopLogger {
    @Autowired
    UserService userService;

    @Autowired
    TributeService tributeService;

    @Autowired
    ShopService shopService;

    @Autowired
    GameService gameService;

    /**
     * Log message for sending present
     * @param joinPoint join point
     */
    @After("execution(* controller.WebSocketController.sendPresent(..)) && within(controller.WebSocketController)")
    public void sendPresentLogger(JoinPoint joinPoint) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        Object[] params  = joinPoint.getArgs();
        User sender = (User) params[0];
        Tribute tribute = (Tribute) params[1];
        Shop present = (Shop) params[2];
        logger.info("Sender: " + sender.getNick() + ", Tribute: " + tribute.getUser().getNick() +", Quantity: " + params[3] + ", Цена: " + present.getCost());
    }

    @AfterThrowing(value = "execution(* controller.WebSocketController.sendPresent(..)) && within(controller.WebSocketController)", throwing = "exc")
    public void catchSendPresentException(JoinPoint joinPoint, Throwable exc) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        Object[] params  = joinPoint.getArgs();
        User sender = (User) params[0];
        Tribute tribute = (Tribute) params[1];
        Shop present = (Shop) params[2];
        logger.error("Error in sending present " + present.getName() + " from " + sender.getNick() + " to "+ tribute.getUser().getNick() + ". Error: " + exc.getClass().getSimpleName());
    }

}
