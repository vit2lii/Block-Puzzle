package sk.tuke.kpi.BlockPuzzle.gamestudio.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Rating {
    private String player;
    private String game;
    private int rating;
    private Date ratedOn;
}
