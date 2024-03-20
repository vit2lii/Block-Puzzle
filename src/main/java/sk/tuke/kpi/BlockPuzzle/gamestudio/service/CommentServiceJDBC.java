package sk.tuke.kpi.BlockPuzzle.gamestudio.service;

import sk.tuke.kpi.BlockPuzzle.gamestudio.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceJDBC implements CommentService {
    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String SELECT = "SELECT game, player, comment, commentedOn FROM comment";
    public static final String DELETE = "DELETE FROM comment";
    public static final String INSERT = "INSERT INTO comment (game, player, comment, commentedOn) VALUES (?, ?, ?, ?)";

    @Override
    public void addComment(Comment comment) throws CommentException {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);
             var statement = connection.prepareStatement(INSERT)
        ) {
            statement.setString(1, comment.getGame());
            statement.setString(2, comment.getPlayer());
            statement.setString(3, comment.getComment());
            statement.setTimestamp(4, new Timestamp(comment.getCommentedOn().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommentException("Problem inserting comment", e);
        }
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);
             var statement = connection.prepareStatement(SELECT)
        ) {
            statement.setString(1, game);
            try (var resultSet = statement.executeQuery()) {
                final List<Comment> comments = new ArrayList<>();
                while (resultSet.next()) {
                    comments.add(new Comment(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getTimestamp(4)));
                }
                return comments;
            }
        } catch (SQLException e) {
            throw new CommentException("Problem selecting comments", e);
        }
    }

    @Override
    public void reset() throws CommentException {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);
             var statement = connection.createStatement();
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new ScoreException("Problem deleting comment", e);
        }
    }
}