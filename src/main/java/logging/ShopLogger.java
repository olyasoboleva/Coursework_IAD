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
import service.ShopService;
import service.TributeService;
import service.UserService;

@Aspect
public class ShopLogger {
    @Autowired
    UserService userService;

    @Autowired
    TributeService tributeService;

    @Autowired
    ShopService shopService;

    /**
     * Log message for sending present
     * @param joinPoint join point
     */
    @After("execution(* controller.WebSocketController.sendPresent(..)) && within(controller.WebSocketController)")
    public void sendPresentLogger(JoinPoint joinPoint) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        Object[] params  = joinPoint.getArgs();
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = tributeService.getTributeById((Long) params[0]);
        Shop product = shopService.getProductById((Integer) params[1]);
        logger.info("Отправитель: " + user.getNick() + ", Трибут: " + tribute.getUser().getNick() +", Количество: " + params[2] + ", Цена: " + product.getCost());
    }

    @AfterThrowing(value = "execution(* controller.WebSocketController.sendPresent(..)) && within(controller.WebSocketController)", throwing = "exc")
    public void catchSendPresentException(JoinPoint joinPoint, Throwable exc) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        Object[] params  = joinPoint.getArgs();
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = tributeService.getTributeById((Long) params[0]);
        Shop product = shopService.getProductById((Integer) params[1]);
        logger.error("Ошибка при отправке подарка " + product.getName() + " пользователем " + user.getNick() + " пользователю "+ tribute.getUser().getNick() + ". Ошибка: " + exc.getClass().getSimpleName());
    }
}