package sk.tuke.gamestudio.service.restclients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.exeptions.CommentException;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentServiceRestClient implements CommentService {
    private final String url = "http://localhost:8080/api/comment";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addComment(Comment comment) throws CommentException {
        restTemplate.postForEntity(url, comment, Comment.class);
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        final var comments = restTemplate.getForEntity(url + "/" + game, Comment[].class).getBody();
        return Arrays.asList(comments != null ? comments : new Comment[0]);
    }

    @Override
    public void reset() throws CommentException {
        throw new UnsupportedOperationException("Not supported via web service");
    }
}
