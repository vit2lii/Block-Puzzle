package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.BlockPuzzle.game.ConsoleBlockPuzzleGame;

@SpringBootApplication
@Configuration
@ComponentScan({"sk.tuke.gamestudio.game.BlockPuzzle", "sk.tuke.gamestudio.service.restclients"})
public class BlockPuzzleApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BlockPuzzleApplication.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public CommandLineRunner runner(ConsoleBlockPuzzleGame gameApplication) {
        return args -> {
            gameApplication.startGame();
        };
    }

    @Bean
    public ConsoleBlockPuzzleGame gameApplication() {
        return new ConsoleBlockPuzzleGame();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
