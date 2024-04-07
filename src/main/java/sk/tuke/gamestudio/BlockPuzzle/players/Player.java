package sk.tuke.gamestudio.BlockPuzzle.players;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Player {
    private final String nickname;
    private final String gamePlaying;
    @Setter
    private int score;

    public Player(String nickname, String gamePlaying) {
        this.nickname = nickname;
        this.gamePlaying = gamePlaying;
        this.score = 0;
    }


}
