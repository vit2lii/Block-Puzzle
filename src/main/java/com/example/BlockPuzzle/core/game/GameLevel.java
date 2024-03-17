package com.example.BlockPuzzle.core.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameLevel {
    EASY(1),
    MEDIUM(2),
    HARD(3),
    TUTORIAL(4),
    INVALID(-1);

    private final int levelValue;

    public static GameLevel getGameLevel(int level) {
        return switch (level) {
            case 1 -> GameLevel.EASY;
            case 2 -> GameLevel.MEDIUM;
            case 3 -> GameLevel.HARD;
            case 4 -> GameLevel.TUTORIAL;
            default -> GameLevel.INVALID;
        };
    }
}
