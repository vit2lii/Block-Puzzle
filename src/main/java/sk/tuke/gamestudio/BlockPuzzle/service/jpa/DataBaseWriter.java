package sk.tuke.gamestudio.BlockPuzzle.service.jpa;

import sk.tuke.gamestudio.BlockPuzzle.entity.Comment;
import sk.tuke.gamestudio.BlockPuzzle.entity.Rating;
import sk.tuke.gamestudio.BlockPuzzle.entity.Score;
import sk.tuke.gamestudio.BlockPuzzle.players.Player;
import sk.tuke.gamestudio.BlockPuzzle.service.*;

import java.util.Date;

public class DataBaseWriter {
    private final ScoreService scoreService;
    private final CommentService commentService;
    private final RatingService ratingService;

    public DataBaseWriter(ScoreService scoreService, CommentService commentService, RatingService ratingService) {
        this.scoreService = scoreService;
        this.commentService = commentService;
        this.ratingService = ratingService;
    }

    public void addComment(Player player, String comment) {
        try {
            final var newComment = new Comment(player.getNickname(), player.getGamePlaying(), comment, new Date());
            commentService.addComment(newComment);
        } catch (CommentException ignored) {
        }
    }

    public void setRating(Player player, String rating) {
        int playerRating;
        try {
            playerRating = Integer.parseInt(rating);
        } catch (NumberFormatException e) {
            return;
        }

        if (playerRating < 1 || playerRating > 5) {
            return;
        }

        try {
            ratingService.setRating(new Rating(player.getNickname(), player.getGamePlaying(), playerRating, new Date()));
        } catch (RatingException ignored) {
        }
    }

    public void setScore(Player player, int score) {
        try {
            scoreService.addScore(new Score(player.getNickname(), player.getGamePlaying(), score, new Date()));
            player.setScore(score);
        } catch (ScoreException ignored) {
        }
    }
}
