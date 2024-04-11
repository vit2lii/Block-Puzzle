package sk.tuke.gamestudio.service.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.exeptions.CommentException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CommentServiceJPATest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CommentServiceJPA commentServiceJPA;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddComment() throws CommentException {
        final var comment = new Comment("player1", "game1", "Great game!", new Date());

        commentServiceJPA.addComment(comment);

        verify(entityManager).persist(comment);
    }

    @Test
    public void testAddCommentNullPlayer() {
        final var comment = new Comment(null, "game1", "Great game!", new Date());

        assertThrows(CommentException.class, () -> commentServiceJPA.addComment(comment));
        verify(entityManager, never()).persist(any());
    }

    @Test
    public void testAddCommentNullGame() {
        var comment = new Comment("player1", null, "Great game!", new Date());

        assertThrows(CommentException.class, () -> commentServiceJPA.addComment(comment));
        verify(entityManager, never()).persist(any());
    }

    @Test
    public void testAddCommentNullComment() {
        var comment = new Comment("player1", "game1", null, new Date());

        assertThrows(CommentException.class, () -> commentServiceJPA.addComment(comment));
        verify(entityManager, never()).persist(any());
    }

    @Test
    public void testAddCommentNullCommentedOn() {
        var comment = new Comment("player1", "game1", "Great game!", null);

        assertThrows(CommentException.class, () -> commentServiceJPA.addComment(comment));
        verify(entityManager, never()).persist(any());
    }

    @Test
    public void testGetComments() throws CommentException {
        var game = "game1";

        var expectedComments = new ArrayList<>();
        expectedComments.add(new Comment("player1", game, "Great game!", new Date()));
        expectedComments.add(new Comment("player2", game, "Awesome!", new Date()));

        var typedQuery = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(eq("Comment.getComments"), eq(Comment.class)))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedComments);

        var comments = commentServiceJPA.getComments(game);

        assertEquals(expectedComments.size(), comments.size());
        assertEquals(expectedComments, comments);
    }

    @Test
    public void testReset() throws CommentException {
        var mockQuery = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(eq("Comment.resetComments"))).thenReturn(mockQuery);
        when(mockQuery.executeUpdate()).thenReturn(1);

        commentServiceJPA.reset();

        verify(entityManager).createNamedQuery(eq("Comment.resetComments"));
        verify(mockQuery).executeUpdate();
    }
}