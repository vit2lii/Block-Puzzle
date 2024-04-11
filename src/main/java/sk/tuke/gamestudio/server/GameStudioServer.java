package sk.tuke.gamestudio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EntityScan("sk.tuke.gamestudio.entity")
@ComponentScan({"sk.tuke.gamestudio.server", "sk.tuke.gamestudio.service.jpa"})
public class GameStudioServer {
    public static void main(String[] args) {
        SpringApplication.run(GameStudioServer.class, args);
    }
}