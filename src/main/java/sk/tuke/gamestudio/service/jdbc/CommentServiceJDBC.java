package sk.tuke.gamestudio.service.jdbc;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.exeptions.CommentException;
import sk.tuke.gamestudio.service.CommentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceJDBC implements CommentService {
    private static final String URL = "jdbc:postgresql://localhost/gamestudio";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String INSERT = "INSERT INTO comment (player, game, comment, commentedon) VALUES (?, ?, ?, ?)";
    private static final String SELECT = "SELECT player, game, comment, commentedon FROM comment WHERE game = ?";
    private static final String DELETE = "DELETE FROM comment";

    @Override
    public void addComment(Comment comment) {
        if (!isCommentValid(comment)) {
            throw new CommentException("Invalid comment.");
        }

        try (final var statement = prepareStatement(INSERT)) {
            setCommentParameters(statement, comment);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommentException("Failed to add comment.", e);
        }
    }

    @Override
    public List<Comment> getComments(String game) {
        if (game == null) {
            throw new CommentException("Invalid game name.");
        }

        try (final var statement = prepareStatement(SELECT)) {
            statement.setString(1, game);
            return extractComments(statement.executeQuery());
        } catch (SQLException e) {
            throw new CommentException("Failed to retrieve comments.", e);
        }
    }

    @Override
    public void reset() {
        try (final var statement = prepareStatement(DELETE)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CommentException("Failed to reset comments.", e);
        }
    }

    private PreparedStatement prepareStatement(final String sql) throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD).prepareStatement(sql);
    }

    private void setCommentParameters(PreparedStatement statement, Comment comment) throws SQLException {
        statement.setString(1, comment.getPlayer());
        statement.setString(2, comment.getGame());
        statement.setString(3, comment.getComment());
        statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
    }

    private List<Comment> extractComments(final ResultSet resultSet) throws SQLException {
        final var scores = new ArrayList<Comment>();

        while (resultSet.next()) {
            scores.add(new Comment(resultSet.getString("player"), resultSet.getString("game"), resultSet.getString("comment"), resultSet.getTimestamp("commentedon")));
        }

        return scores;
    }

    private boolean isCommentValid(Comment comment) {
        return comment != null && comment.getGame() != null && comment.getPlayer() != null && comment.getComment() != null && comment.getCommentedOn() != null;
    }
}
