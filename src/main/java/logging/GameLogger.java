package logging;

import entity.*;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import service.*;

import java.util.Collection;


@Aspect
public class GameLogger {

    @Autowired
    UserService userService;

    @Autowired
    TributeService tributeService;

    @Autowired
    PriceService priceService;


    /**
     * Log message for daily bonus
     * @param joinPoint join point
     */
    @After("execution(* service.UserService.updateUserLastActivity(..)) && within(service.UserService)")
    public void sendBonus(JoinPoint joinPoint) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        int price = priceService.getPriceByName("Ежедневный приз").getCost();
        logger.info("User: " + user.getNick() + "Bonus: " + price + "Balance: " + user.getCash());
    }

    @AfterThrowing(value = "execution(* service.UserService.updateUserLastActivity(..)) && within(service.UserService)", throwing = "exc")
    public void catchSendBonusException(JoinPoint joinPoint, Throwable exc) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        User user = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        logger.error("Ошибка при отправке ежедневного бонуса пользователю " + user.getNick() + ". Error: " + exc.getClass().getSimpleName());
    }


    /**
     * Price for game winner and sponsors
     * @param joinPoint join point
     */
    @After("execution(* service.GameProcessService.changeStatusAfterEndOfTheGame(..)) && within(service.GameProcessService)")
    public void paymentForWinner(JoinPoint joinPoint) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        Object[] params  = joinPoint.getArgs();
        Tribute tribute = (Tribute) params[1];
        Game game = (Game) params[0];
        logger.info("Game " + game.getGameId() + ", Tribute-winner: " + tribute.getUser().getNick()+ ", Salary: "  + priceService.getPriceByName("winner").getCost() + ", Balance: " + tribute.getUser().getCash());
        int sponsorProfit = priceService.getPriceByName("winner's sponsor").getCost();
        Collection<User> sponsors = tribute.getPresentsSenders();
        for (User sponsor: sponsors){
            logger.info("Game " + game.getGameId() + "Salary to sponsor: " + sponsor.getNick() + ", Salary: " + sponsorProfit + ", Balance: " + sponsor.getCash());
        }
    }

    @AfterThrowing(value = "execution(* service.GameProcessService.changeStatusAfterEndOfTheGame(..)) && within(service.GameProcessService)", throwing = "exc")
    public void catchPaymentForWinnerException(JoinPoint joinPoint, Throwable exc) {
        final Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
        Object[] params  = joinPoint.getArgs();
        Tribute tribute = (Tribute) params[1];
        Game game = (Game) params[0];
        logger.error("Ошибка при выдаче зарплаты победителю" + tribute.getUser().getNick() +" или спонсорам в игре " + game.getGameId()  + ". Ошибка: " + exc.getClass().getSimpleName());
    }

}
