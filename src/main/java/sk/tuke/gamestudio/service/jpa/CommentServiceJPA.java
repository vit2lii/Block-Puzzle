package sk.tuke.gamestudio.service.jpa;

import org.springframework.stereotype.Service;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.exeptions.CommentException;
import sk.tuke.gamestudio.service.CommentService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CommentServiceJPA implements CommentService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addComment(Comment comment) throws CommentException {
        if(comment == null || comment.getPlayer() == null || comment.getGame() == null || comment.getComment() == null || comment.getCommentedOn() == null || comment.getPlayer().isBlank() || comment.getGame().isBlank() || comment.getComment().isBlank()) {
            throw new CommentException("Invalid comment");
        }
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        return entityManager.createNamedQuery("Comment.getComments", Comment.class)
                .setParameter("game", game)
                .getResultList();
    }

    @Override
    public void reset() throws CommentException {
        entityManager.createNamedQuery("Comment.resetComments").executeUpdate();
    }
}
