package sk.tuke.gamestudio.entity;

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
@NamedQuery( name = "Comment.getComments",
        query = "SELECT c FROM Comment c WHERE c.game=:game ORDER BY c.comment DESC")
@NamedQuery( name = "Comment.resetComments",
        query = "DELETE FROM Comment")
public class Comment implements Serializable {
    @Id
    @SequenceGenerator(
            name = "comment_seq",
            sequenceName = "comment_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_seq"
    )
    private int ident;
    private String player;
    private String game;
    private String comment;
    private Date commentedOn;

    public Comment() {
    }

    public Comment(String player, String game, String comment, Date commentedOn) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commentedOn = commentedOn;
    }
}
