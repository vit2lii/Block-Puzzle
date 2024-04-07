package sk.tuke.gamestudio.BlockPuzzle.service.jpa;

import org.springframework.stereotype.Service;
import sk.tuke.gamestudio.BlockPuzzle.entity.Comment;
import sk.tuke.gamestudio.BlockPuzzle.service.CommentException;
import sk.tuke.gamestudio.BlockPuzzle.service.CommentService;

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
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        return entityManager.createNamedQuery("Comment.getComments")
                .setParameter("game", game)
                .getResultList();
    }

    @Override
    public void reset() throws CommentException {
        entityManager.createNamedQuery("Comment.resetComments").executeUpdate();
    }
}
