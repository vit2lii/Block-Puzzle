package sk.tuke.gamestudio.service.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.exeptions.ScoreException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ScoreServiceJPATest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ScoreServiceJPA scoreServiceJPA;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddScore() throws ScoreException {
        var score = new Score("player1", "game1", 100, new Date());

        var typedQuery = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(eq("Score.getExistingPlayer"), eq(Score.class)))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(NoResultException.class);

        scoreServiceJPA.addScore(score);

        verify(entityManager).persist(score);
    }

    @Test
    public void testGetTopScores() throws ScoreException {
        var game = "game1";
        var expectedScores = List.of(new Score("player1", game, 100, new Date()),
                new Score("player2", game, 90, new Date()));

        var typedQuery = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(eq("Score.getTopScores")))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(anyInt())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedScores);

        var topScores = scoreServiceJPA.getTopScores(game);

        assertEquals(expectedScores, topScores);
    }

    @Test
    public void testReset() throws ScoreException {
        var mockQuery = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(eq("Score.resetScore"))).thenReturn(mockQuery);
        when(mockQuery.executeUpdate()).thenReturn(1);

        scoreServiceJPA.reset();

        verify(entityManager).createNamedQuery(eq("Score.resetScore"));
        verify(mockQuery).executeUpdate();
    }

    @Test
    public void testAddScoreNullGame() {
        var score = new Score("player1", null, 100, new Date());
        assertThrows(ScoreException.class, () -> scoreServiceJPA.addScore(score));
        verify(entityManager, never()).persist(any());
    }

    @Test
    public void testAddScoreNegativePoints() {
        var score = new Score("player1", "game1", -50, new Date());
        assertThrows(ScoreException.class, () -> scoreServiceJPA.addScore(score));
        verify(entityManager, never()).persist(any());
    }
}