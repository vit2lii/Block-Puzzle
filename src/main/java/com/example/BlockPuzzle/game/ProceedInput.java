package com.example.BlockPuzzle.game;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public enum ProceedInput {
    CONTINUE_PLAYING(1),
    LEAVE(2),
    INVALID(-1);

    private final int choice;

    public static ProceedInput getProceedPlayingChoice(int level) {
        return switch (level) {
            case 1 -> ProceedInput.CONTINUE_PLAYING;
            case 2 -> ProceedInput.LEAVE;
            default -> ProceedInput.INVALID;
        };
    }
}
