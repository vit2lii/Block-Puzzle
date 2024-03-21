package sk.tuke.kpi.BlockPuzzle.parser;

import sk.tuke.kpi.BlockPuzzle.core.utilities.Coordinate;
import sk.tuke.kpi.BlockPuzzle.game.levels.GameLevel;

import java.util.regex.Pattern;

public class Parser {
    private static final Pattern GAME_LEVEL_PATTERN = Pattern.compile("^\\s*(\\d)\\s*$");
    private static final Pattern GAME_PLAY_PATTERN = Pattern.compile("^\\s*(\\d+)\\s*$");
    private static final Pattern YES_NO_PATTERN = Pattern.compile("^\\s*(?:y(?:es)?|no?)\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PLACE_BLOCK_PATTERN = Pattern.compile("^\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$");
    private static final Pattern REMOVE_BLOCK_PATTERN = Pattern.compile("^\\s*(\\d+)\\s+(\\d+)\\s*$");

    public static GameLevel parseGameLevelInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return GameLevel.INVALID;
        }

        final var gameLevelMatcher = GAME_LEVEL_PATTERN.matcher(input);
        if (gameLevelMatcher.find()) {
            try {
                final var level = Integer.parseInt(gameLevelMatcher.group(1));
                return GameLevel.getGameLevel(level);
            } catch (NumberFormatException e) {
                return GameLevel.INVALID;
            }
        }

        return GameLevel.INVALID;
    }

    public static GamePlayType parseGamePlayTypeInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return GamePlayType.INVALID;
        }

        final var gameLevelMatcher = GAME_PLAY_PATTERN.matcher(input);
        if (gameLevelMatcher.find()) {
            try {
                final var level = Integer.parseInt(gameLevelMatcher.group(1));
                return GamePlayType.getGamePlayType(level);
            } catch (NumberFormatException e) {
                return GamePlayType.INVALID;
            }
        }

        return GamePlayType.INVALID;
    }

    public static Coordinate removeBlockInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        final var removeBlockMatcher = REMOVE_BLOCK_PATTERN.matcher(input);
        if (removeBlockMatcher.find()) {
            try {
                final var xCoordinate = Integer.parseInt(removeBlockMatcher.group(1));
                final var yCoordinate = Integer.parseInt(removeBlockMatcher.group(2));
                return new Coordinate(xCoordinate, yCoordinate);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return null;
    }

    public static PlaceBlockInput placeBlockInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        final var removeBlockMatcher = PLACE_BLOCK_PATTERN.matcher(input);
        if (removeBlockMatcher.find()) {
            try {
                final var blockIndex = Integer.parseInt(removeBlockMatcher.group(1));
                final var xCoordinate = Integer.parseInt(removeBlockMatcher.group(2));
                final var yCoordinate = Integer.parseInt(removeBlockMatcher.group(3));
                return new PlaceBlockInput(blockIndex, new Coordinate(xCoordinate, yCoordinate));
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return null;
    }

    public static YesNoInput yesNoInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return YesNoInput.INVALID;
        }

        final var continuePlayingMatcher = YES_NO_PATTERN.matcher(input);
        if (continuePlayingMatcher.find()) {
            final var choice = continuePlayingMatcher.group().toLowerCase();
            if (choice.startsWith("y")) {
                return YesNoInput.YES;
            } else if (choice.startsWith("n")) {
                return YesNoInput.NO;
            }
        }

        return YesNoInput.INVALID;
    }
}
