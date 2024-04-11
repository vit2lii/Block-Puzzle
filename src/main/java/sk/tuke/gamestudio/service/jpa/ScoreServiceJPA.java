package sk.tuke.gamestudio.service.jpa;

import org.springframework.stereotype.Service;
import sk.tuke.gamestudio.service.exeptions.ScoreException;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.entity.Score;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ScoreServiceJPA implements ScoreService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) throws ScoreException {
        if (score == null || score.getPlayer() == null || score.getGame() == null || score.getPoints() < 0 || score.getPlayedOn() == null || score.getPlayer().isBlank() || score.getGame().isBlank()){
            throw new ScoreException("Invalid score");
        }

        final var previousPlayerScore = getExistingPlayerPreviousScore(score);

        if (previousPlayerScore != null) {
            entityManager.createNamedQuery("Score.updateExistingPlayerScore")
                    .setParameter("points", score.getPoints())
                    .setParameter("playedOn", score.getPlayedOn())
                    .setParameter("player", score.getPlayer())
                    .setParameter("game", score.getGame())
                    .executeUpdate();
        } else {
            entityManager.persist(score);
        }
    }

    @Override
    public List<Score> getTopScores(String game) throws ScoreException {
        return entityManager.createNamedQuery("Score.getTopScores")
                .setParameter("game", game).setMaxResults(10).getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNamedQuery("Score.resetScore").executeUpdate();
    }

    private Score getExistingPlayerPreviousScore(Score score) {
        try {
            return entityManager.createNamedQuery("Score.getExistingPlayer", Score.class)
                    .setParameter("player", score.getPlayer())
                    .setParameter("game", score.getGame())
                    .getSingleResult();
        } catch (NoResultException ignored) {
            return null;
        }
    }
}
