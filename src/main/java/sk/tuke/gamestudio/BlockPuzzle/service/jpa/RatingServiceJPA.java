package sk.tuke.gamestudio.BlockPuzzle.service.jpa;

import org.springframework.stereotype.Service;
import sk.tuke.gamestudio.BlockPuzzle.entity.Rating;
import sk.tuke.gamestudio.BlockPuzzle.service.RatingException;
import sk.tuke.gamestudio.BlockPuzzle.service.RatingService;

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
        Rating existingRating = null;
        try {
            existingRating = entityManager.createNamedQuery("Rating.getExistingPlayer", Rating.class)
                    .setParameter("player", rating.getPlayer())
                    .setParameter("game", rating.getGame())
                    .getSingleResult();
        } catch (NoResultException ignored) {
            // do nothing
        }

        if (existingRating != null) {
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
        Double averageRating = (Double) entityManager.createNamedQuery("Rating.getAverageRating")
                .setParameter("game", game)
                .getSingleResult();

        if (averageRating == null) {
            return 0;
        }

        return averageRating.intValue();
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        return entityManager.createNamedQuery("Rating.getPlayerRating")
                .setParameter("player", player)
                .setParameter("game", game)
                .executeUpdate();
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRating").executeUpdate();
    }
}
