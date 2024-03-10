package com.example.BlockPuzzle.core.game.levels;

public class LevelFactory {
    public Level createLevel(int levelNumber) {
        switch (levelNumber) {
            case 1:
                return new EasyLevel();
            case 2:
                return new MediumLevel();
            case 3:
                return new HardLevel();
            case 4:
                return new TutorialLevel();
            default:
                return null;
        }
    }
}
