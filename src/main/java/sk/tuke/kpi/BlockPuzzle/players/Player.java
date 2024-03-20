package sk.tuke.kpi.BlockPuzzle.players;

import lombok.Getter;
import sk.tuke.kpi.BlockPuzzle.gamestudio.entity.Comment;
import sk.tuke.kpi.BlockPuzzle.gamestudio.entity.Rating;
import sk.tuke.kpi.BlockPuzzle.gamestudio.entity.Score;
import sk.tuke.kpi.BlockPuzzle.gamestudio.service.*;

import java.util.Date;

@Getter
public class Player {
    private String nickname;
    private int score;

    public Player(String nickname) {
        this.nickname = nickname;
        this.score = 0;
    }

    public void addComment(String game, String comment) {
        try {
            final var newComment = new Comment(nickname, game, comment, new Date());
            final var commentService = new CommentServiceJDBC();
            commentService.addComment(newComment);
        } catch (CommentException e) {
            // do nothing
        }
    }

    public void setRating(String game, String rating) {
        int playerRating = 0;
        try {
            playerRating = Integer.parseInt(rating);
        } catch (NumberFormatException e) {
            return;
        }

        if (playerRating < 1 || playerRating > 5) {
            return;
        }

        try {
            final var ratingService = new RatingServiceJDBC();
            ratingService.setRating(new Rating(nickname, game, playerRating, new Date()));
        } catch (RatingException e) {
            // do nothing
        }
    }

    public void setScore(String game, int score) {
        try {
            final var scoreService = new ScoreServiceJDBC();
            scoreService.addScore(new Score(nickname, game, score, new Date()));
            this.score = score;
        } catch (ScoreException e) {
            // do nothing
        }
    }
}
