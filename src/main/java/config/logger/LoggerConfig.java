package config.logger;

import logging.GameLogger;
import logging.ShopLogger;
import logging.TrainingLogger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@ComponentScan(basePackages = {"logging"},
        excludeFilters = {@ComponentScan.Filter(Aspect.class)})
public class LoggerConfig {

    @Bean
    public GameLogger gameLogger() {
        return new GameLogger();
    }

    @Bean
    public TrainingLogger trainingLogger() {
        return new TrainingLogger();
    }

    @Bean
    public ShopLogger shopLogger() {
        return new ShopLogger();
    }
}
