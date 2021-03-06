
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import repository.ShopRepository;
import service.ShopService;

@SpringBootApplication
@ComponentScan(basePackages = {"logging", "service", "entity", "controller", "impl", "config.security", "config.websocket", "config.logger"})
@EntityScan("entity")
@EnableJpaRepositories("repository")
@EnableScheduling
//FIXME: раскомментировать потом, for frontend
//@EnableOAuth2Sso
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}