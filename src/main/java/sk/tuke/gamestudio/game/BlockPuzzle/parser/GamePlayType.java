package sk.tuke.gamestudio.game.BlockPuzzle.parser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GamePlayType {
    PLACE_BLOCK(1),
    REMOVE_BLOCK(2),
    SURRENDER(3),
    INVALID(-1);

    private final int levelValue;

    public static GamePlayType getGamePlayType(int level) {
        return switch (level) {
            case 1 -> GamePlayType.PLACE_BLOCK;
            case 2 -> GamePlayType.REMOVE_BLOCK;
            case 3 -> GamePlayType.SURRENDER;
            default -> GamePlayType.INVALID;
        };
    }
}
