package sk.tuke.gamestudio.service.jdbc;

import sk.tuke.gamestudio.service.exeptions.RatingException;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.entity.Rating;

import java.sql.*;

public class RatingServiceJDBC implements RatingService {
    private static final String DB_URL = "jdbc:postgresql://localhost/gamestudio";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String SELECT_AVERAGE = "SELECT AVG(rating) FROM rating WHERE game = ?";
    private static final String SELECT_PLAYER = "SELECT game, player, rating, ratedOn FROM rating WHERE player = ?";
    private static final String DELETE_RATINGS = "DELETE FROM rating";
    private static final String INSERT_RATING = "INSERT INTO rating (game, player, rating, ratedOn) VALUES (?, ?, ?, ?)";

    @Override
    public void setRating(Rating rating) throws RatingException {
        try (var connection = getConnection();
             var statement = connection.prepareStatement(INSERT_RATING)) {
            if (!playerExists(connection, rating.getPlayer())) {
                addNewPlayerRating(statement, rating);
            } else {
                updateExistingPlayerRating(connection, rating);
            }
        } catch (SQLException e) {
            throw new RatingException("Problem inserting rating", e);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        try (var connection = getConnection();
             var statement = connection.prepareStatement(SELECT_AVERAGE)) {
            statement.setString(1, game);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RatingException("Problem selecting average rating", e);
        }
        return -1;
    }

    @Override
    public int getRating(String player, String game) throws RatingException {
        try (var connection = getConnection();
             var statement = connection.prepareStatement(SELECT_PLAYER)) {
            statement.setString(1, player);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    final var rating = new Rating(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getTimestamp(4));
                    return rating.getRating();
                }
            }
        } catch (SQLException e) {
            throw new RatingException("Problem selecting rating", e);
        }
        return -1;
    }

    @Override
    public void reset() throws RatingException {
        try (var connection = getConnection();
             var statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_RATINGS);
        } catch (SQLException e) {
            throw new RatingException("Problem deleting ratings", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private boolean playerExists(Connection connection, String player) throws SQLException {
        final var sql = "SELECT 1 FROM rating WHERE player = ?";
        try (var statement = connection.prepareStatement(sql)) {
            statement.setString(1, player);
            try (var resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private void addNewPlayerRating(PreparedStatement statement, Rating rating) throws SQLException {
        statement.setString(1, rating.getGame());
        statement.setString(2, rating.getPlayer());
        statement.setInt(3, rating.getRating());
        statement.setTimestamp(4, new Timestamp(rating.getRatedOn().getTime()));
        statement.executeUpdate();
    }

    private void updateExistingPlayerRating(Connection connection, Rating rating) throws SQLException {
        final var updateColumn = "UPDATE rating SET rating = ?, ratedOn = ? WHERE player = ?";
        try (var statement = connection.prepareStatement(updateColumn)) {
            statement.setInt(1, rating.getRating());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setString(3, rating.getPlayer());
            statement.executeUpdate();
        }
    }
}
