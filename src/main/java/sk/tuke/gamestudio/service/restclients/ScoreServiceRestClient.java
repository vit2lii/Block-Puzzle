package sk.tuke.gamestudio.service.restclients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.Arrays;
import java.util.List;

@Service
public class ScoreServiceRestClient implements ScoreService {
    private final String url = "http://localhost:8080/api/score";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url, score, Score.class);
    }

    @Override
    public List<Score> getTopScores(String game) {
        final var scores = restTemplate.getForEntity(url + "/" + game, Score[].class).getBody();
        return Arrays.asList(scores != null ? scores : new Score[0]);
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
    }
}