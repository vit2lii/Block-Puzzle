package sk.tuke.gamestudio.game.BlockPuzzle.game.levels;

public class LevelFactory {
    public static Level createLevel(GameLevel level) {
        switch (level) {
            case EASY:
                return new EasyLevel();
            case MEDIUM:
                return new MediumLevel();
            case HARD:
                return new HardLevel();
            case DAILY_PUZZLE:
                return new RandomPuzzle();
            default:
                return null;
        }
    }
}
