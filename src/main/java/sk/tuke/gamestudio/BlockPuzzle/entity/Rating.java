package sk.tuke.gamestudio.BlockPuzzle.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NamedQuery( name = "Rating.getAverageRating",
        query = "SELECT AVG(r.rating) FROM Rating r WHERE r.game=:game")
@NamedQuery( name = "Rating.getPlayerRating",
        query = "SELECT r.rating FROM Rating r WHERE r.player=:player AND r.game=:game")
@NamedQuery( name = "Rating.resetRating",
        query = "DELETE FROM Rating ")
@NamedQuery( name = "Rating.updateExistingPlayerRating",
    query = "UPDATE Rating r SET r.rating=:rating, r.ratedOn=:ratedOn WHERE r.player=:player AND r.game=:game")
@NamedQuery( name = "Rating.getExistingPlayer",
        query = "SELECT r FROM Rating r WHERE r.player=:player AND r.game =:game")
public class Rating implements Serializable {
    @Id
    @SequenceGenerator(
            name = "rating_seq",
            sequenceName = "rating_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rating_seq"
    )
    private int ident;
    private String player;
    private String game;
    private int rating;
    private Date ratedOn;

    public Rating() {
    }

    public Rating(String player, String game, int rating, Date ratedOn) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }
}
