package sk.tuke.gamestudio.service.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.exeptions.RatingException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RatingServiceJPATest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private RatingServiceJPA ratingServiceJPA;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSetRating() throws RatingException {
        var rating = new Rating("player1", "game1", 5, new Date());

        var typedQuery = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(eq("Rating.getExistingPlayer"), eq(Rating.class)))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(NoResultException.class);

        ratingServiceJPA.setRating(rating);

        verify(entityManager).persist(rating);
    }

    @Test
    public void testSetRatingException() {
        var rating = new Rating("player1", "game1", 5, null);

        var typedQuery = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(eq("Rating.getExistingPlayer"), eq(Rating.class)))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(NoResultException.class);

        assertThrows(RatingException.class, () -> ratingServiceJPA.setRating(rating));
    }

    @Test
    public void testSetRatingInvalidRating() {
        var invalidRating = new Rating("player1", "game1", 6, new Date());
        assertThrows(RatingException.class, () -> ratingServiceJPA.setRating(invalidRating));
    }

    @Test
    public void testSetRatingNullPlayerOrGame() {
        var ratingNullPlayer = new Rating(null, "game1", 4, new Date());
        var ratingNullGame = new Rating("player1", null, 3, new Date());
        assertThrows(RatingException.class, () -> ratingServiceJPA.setRating(ratingNullPlayer));
        assertThrows(RatingException.class, () -> ratingServiceJPA.setRating(ratingNullGame));
    }

    @Test
    public void testGetAverageRating() throws RatingException {
        var game = "game1";
        var expectedAverageRating = 4.5;

        var typedQuery = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(eq("Rating.getAverageRating")))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(expectedAverageRating);

        var averageRating = ratingServiceJPA.getAverageRating(game);

        assertEquals(4, averageRating);
    }

    @Test
    public void testReset() throws RatingException {
        var mockQuery = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(eq("Rating.resetRating"))).thenReturn(mockQuery);
        when(mockQuery.executeUpdate()).thenReturn(1);

        ratingServiceJPA.reset();

        verify(entityManager).createNamedQuery(eq("Rating.resetRating"));
        verify(mockQuery).executeUpdate();
    }

    @Test
    public void testGetRating() throws RatingException {
        var game = "game1";
        var player = "player1";
        int expectedRating = 4;

        var typedQuery = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(eq("Rating.getPlayerRating")))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(expectedRating);

        var playerRating = ratingServiceJPA.getRating(game, player);

        assertEquals(expectedRating, playerRating);
    }

    @Test
    public void testGetRatingException() {
        String game = null;
        String player = null;
        assertThrows(RatingException.class, () -> ratingServiceJPA.getRating(game, player));
    }
}
