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
@NamedQuery( name = "Score.getTopScores",
        query = "SELECT s FROM Score s WHERE s.game=:game ORDER BY s.points DESC")
@NamedQuery( name = "Score.resetScore",
        query = "DELETE FROM Score")
@NamedQuery( name = "Score.updateExistingPlayerScore",
    query = "UPDATE Score s SET s.points=:points, s.playedOn=:playedOn WHERE s.player=:player AND s.game=:game")
@NamedQuery( name = "Score.getExistingPlayer",
        query = "SELECT s FROM Score s WHERE s.player=:player AND s.game=:game")
public class Score implements Serializable {
    @Id
    @SequenceGenerator(
            name = "score_seq",
            sequenceName = "score_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "score_seq"
    )
    private int ident;

    private String player;
    private String game;
    private int points;
    private Date playedOn;

    public Score() {
    }

    public Score(String player, String game, int points, Date playedOn) {
        this.player = player;
        this.game = game;
        this.points = points;
        this.playedOn = playedOn;
    }
}
