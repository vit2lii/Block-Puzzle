package sk.tuke.gamestudio.game.BlockPuzzle.game.levels;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameLevel {
    EASY(1),
    MEDIUM(2),
    HARD(3),
    DAILY_PUZZLE(4),
    EXIT(5),
    INVALID(-1);

    private final int levelValue;

    public static GameLevel getGameLevel(int level) {
        return switch (level) {
            case 1 -> GameLevel.EASY;
            case 2 -> GameLevel.MEDIUM;
            case 3 -> GameLevel.HARD;
            case 4 -> GameLevel.DAILY_PUZZLE;
            case 5 -> GameLevel.EXIT;
            default -> GameLevel.INVALID;
        };
    }
}
