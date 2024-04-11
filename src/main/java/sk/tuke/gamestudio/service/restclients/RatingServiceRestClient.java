package sk.tuke.gamestudio.service.restclients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;

@Service
public class RatingServiceRestClient implements RatingService {
    private static final String url = "http://localhost:8080/api/rating";

     @Autowired
    private RestTemplate restTemplate;

    @Override
    public void setRating(Rating rating) {
        restTemplate.postForEntity(url, rating, Rating.class);
    }

    @Override
    public int getAverageRating(String game) {
        final var rating = restTemplate.getForObject(url + "/" + game, Double.class);
        return rating != null ? rating.intValue() : 0;
    }

    @Override
    public int getRating(String game, String player) {
        final var rating = restTemplate.getForEntity(url + "/" + game + "/" + player, Double.class).getBody();
        return rating != null ? rating.intValue() : 0;
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
    }
}
