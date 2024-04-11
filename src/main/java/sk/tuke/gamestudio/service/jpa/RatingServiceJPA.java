package sk.tuke.gamestudio.service.jpa;

import org.springframework.stereotype.Service;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.exeptions.RatingException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Service
public class RatingServiceJPA implements RatingService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {
        if (isNotValidParameter(rating)) {
            throw new RatingException("Invalid rating");
        }

        final var previousPlayerRating = getExistingPlayerPreviousRating(rating);

        if (previousPlayerRating != null) {
            entityManager.createNamedQuery("Rating.updateExistingPlayerRating")
                    .setParameter("rating", rating.getRating())
                    .setParameter("ratedOn", rating.getRatedOn())
                    .setParameter("player", rating.getPlayer())
                    .setParameter("game", rating.getGame())
                    .executeUpdate();
        } else {
            entityManager.persist(rating);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        if (isNotValidParameter(game)) {
            throw new RatingException("Game cannot be null");
        }

        final var averageRating = (Double) entityManager.createNamedQuery("Rating.getAverageRating")
                .setParameter("game", game)
                .getSingleResult();

        if (averageRating == null) {
            return 0;
        }

        return averageRating.intValue();
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        if (isNotValidParameter(game) || isNotValidParameter(player)) {
            throw new RatingException("Game cannot be null");
        }

        try {
            return (int) entityManager.createNamedQuery("Rating.getPlayerRating")
                    .setParameter("player", player)
                    .setParameter("game", game)
                    .getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRating").executeUpdate();
    }

    private Rating getExistingPlayerPreviousRating(Rating newRating) {
        try {
            return entityManager.createNamedQuery("Rating.getExistingPlayer", Rating.class)
                    .setParameter("player", newRating.getPlayer())
                    .setParameter("game", newRating.getGame())
                    .getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }

    private boolean isNotValidParameter(Rating rating) {
        return rating == null || rating.getGame() == null || rating.getPlayer() == null || rating.getRating() <= 0 || rating.getRating() > 5 || rating.getRatedOn() == null || rating.getGame().isBlank() || rating.getPlayer().isBlank();
    }

    private boolean isNotValidParameter(String parameter) {
        return parameter == null || parameter.isBlank();
    }
}
