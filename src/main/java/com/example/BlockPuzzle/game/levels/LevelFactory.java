package com.example.BlockPuzzle.game.levels;

public class LevelFactory {
    public Level createLevel(GameLevel level) {
        switch (level) {
            case EASY:
                return new EasyLevel();
            case MEDIUM:
                return new MediumLevel();
            case HARD:
                return new HardLevel();
            case DAILY_PUZZLE:
                return new DailyPuzzle();
            default:
                return null;
        }
    }
}
