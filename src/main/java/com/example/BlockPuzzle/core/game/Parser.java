package com.example.BlockPuzzle.core.game;

import com.example.BlockPuzzle.core.utilities.Coordinate;

import java.util.regex.Pattern;

public class Parser {
    private static final Pattern GAME_LEVEL_PATTERN = Pattern.compile("\\s*(\\d+)\\s*");
    private static final Pattern GAME_PLAY_PATTERN = Pattern.compile("\\s*(\\d+)\\s*");
    private static final Pattern PLACE_BLOCK_PATTERN = Pattern.compile("\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*");
    private static final Pattern REMOVE_BLOCK_PATTERN = Pattern.compile("\\s*(\\d+)\\s+(\\d+)\\s*");

    public static GameLevel parseGameLevelInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return GameLevel.INVALID;
        }

        var gameLevelMatcher = GAME_LEVEL_PATTERN.matcher(input);
        if (gameLevelMatcher.find()) {
           var level = Integer.parseInt(gameLevelMatcher.group(1));
           return GameLevel.getGameLevel(level);
        }

        return GameLevel.INVALID;
    }

    public static GamePlayType parseGamePlayTypeInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return GamePlayType.INVALID;
        }

        var gameLevelMatcher = GAME_PLAY_PATTERN.matcher(input);
        if (gameLevelMatcher.find()) {
            var level = Integer.parseInt(gameLevelMatcher.group(1));
            return GamePlayType.getGamePlayType(level);
        }

        return GamePlayType.INVALID;
    }

    public static Coordinate removeBlockInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        var removeBlockMatcher = REMOVE_BLOCK_PATTERN.matcher(input);
        if (removeBlockMatcher.find()) {
            var xCoordinate = Integer.parseInt(removeBlockMatcher.group(1));
            var yCoordinate = Integer.parseInt(removeBlockMatcher.group(2));
            return new Coordinate(xCoordinate, yCoordinate);
        }

        return null;
    }

    public static PlaceBlockInput placeBlockInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        var removeBlockMatcher = PLACE_BLOCK_PATTERN.matcher(input);
        if (removeBlockMatcher.find()) {
            var blockIndex = Integer.parseInt(removeBlockMatcher.group(1));
            var xCoordinate = Integer.parseInt(removeBlockMatcher.group(2));
            var yCoordinate = Integer.parseInt(removeBlockMatcher.group(3));
            return new PlaceBlockInput(blockIndex, new Coordinate(xCoordinate, yCoordinate));
        }

        return null;
    }
}
