package sk.tuke.kpi.BlockPuzzle.gamestudio.service;

import sk.tuke.kpi.BlockPuzzle.gamestudio.entity.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreServiceJDBC implements ScoreService {
    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String SELECT = "SELECT game, player, points, playedOn FROM score WHERE game = ? ORDER BY points DESC LIMIT 10";
    public static final String DELETE = "DELETE FROM score";
    public static final String INSERT = "INSERT INTO score (game, player, points, playedOn) VALUES (?, ?, ?, ?)";

    @Override
    public void addScore(Score score) {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);
             var statement = connection.prepareStatement(INSERT)
        ) {
            if (!playerExists(connection, score.getPlayer())) {
                addNewPlayerScore(statement, score);
            } else {
                updateExistingPlayerScore(connection, score);
            }
        } catch (SQLException e) {
            throw new ScoreException("Problem inserting score", e);
        }
    }

    @Override
    public List<Score> getTopScores(String game) {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);
             var statement = connection.prepareStatement(SELECT)
        ) {
            statement.setString(1, game);
            try (ResultSet rs = statement.executeQuery()) {
                final List<Score> scores = new ArrayList<>();
                while (rs.next()) {
                    scores.add(new Score(rs.getString(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getTimestamp(4)));
                }
                return scores;
            }
        } catch (SQLException e) {
            throw new ScoreException("Problem selecting score", e);
        }
    }

    @Override
    public void reset() {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);
             var statement = connection.createStatement()
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new ScoreException("Problem deleting score", e);
        }
    }

    private boolean playerExists(Connection connection, String player) throws SQLException {
        final var sql = "SELECT 1 FROM score WHERE player = ?";

        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, player);
            try (var resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private void addNewPlayerScore(PreparedStatement statement, Score score) throws SQLException {
        statement.setString(1, score.getGame());
        statement.setString(2, score.getPlayer());
        statement.setInt(3, score.getPoints());
        statement.setTimestamp(4, new Timestamp(score.getPlayedOn().getTime()));
        statement.executeUpdate();
    }

    private void updateExistingPlayerScore(Connection connection, Score score) {
        final var updateColumn = "UPDATE score SET points = ? WHERE player = ?";

        try (var statement = connection.prepareStatement(updateColumn)) {
            statement.setInt(1, score.getPoints());
            statement.setString(2, score.getPlayer());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ScoreException("Problem with updating player`s score" + e);
        }
    }
}