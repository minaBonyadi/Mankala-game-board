package come.example.mancala;
import come.example.mancala.game.service.GameManagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MancalaApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MancalaApplication.class, args);
        GameManagement service = applicationContext.getBean(GameManagement.class);
        service.play();
    }
}
