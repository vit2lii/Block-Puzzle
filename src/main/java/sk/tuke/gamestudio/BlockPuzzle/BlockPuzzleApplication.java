package sk.tuke.gamestudio.BlockPuzzle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.BlockPuzzle.game.ConsoleBlockPuzzleGame;
import sk.tuke.gamestudio.BlockPuzzle.service.CommentService;
import sk.tuke.gamestudio.BlockPuzzle.service.RatingService;
import sk.tuke.gamestudio.BlockPuzzle.service.ScoreService;
import sk.tuke.gamestudio.BlockPuzzle.service.jpa.CommentServiceJPA;
import sk.tuke.gamestudio.BlockPuzzle.service.jpa.RatingServiceJPA;
import sk.tuke.gamestudio.BlockPuzzle.service.jpa.ScoreServiceJPA;

@SpringBootApplication
@Configuration
public class BlockPuzzleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockPuzzleApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ConsoleBlockPuzzleGame consoleBlockPuzzleGame, ScoreService scoreService, RatingService ratingService, CommentService commentService) {
        return args -> {
            consoleBlockPuzzleGame.startGame();
        };
    }

    @Bean
    public ConsoleBlockPuzzleGame consoleBlockPuzzleGame() {
        return new ConsoleBlockPuzzleGame();
    }

    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceJPA();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceJPA();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceJPA();
    }
}
