package sk.tuke.gamestudio.BlockPuzzle.service.jpa;

import org.springframework.stereotype.Service;
import sk.tuke.gamestudio.BlockPuzzle.entity.Score;
import sk.tuke.gamestudio.BlockPuzzle.service.ScoreException;
import sk.tuke.gamestudio.BlockPuzzle.service.ScoreService;

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
        Score existingScore = null;
        try {
            existingScore = entityManager.createNamedQuery("Score.getExistingPlayer", Score.class)
                    .setParameter("player", score.getPlayer())
                    .setParameter("game", score.getGame())
                    .getSingleResult();
        } catch (NoResultException ignored) {
            // do nothing
        }

        if (existingScore != null) {
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
}
