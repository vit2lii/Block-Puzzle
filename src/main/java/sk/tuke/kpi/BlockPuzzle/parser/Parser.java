package sk.tuke.kpi.BlockPuzzle.parser;

import sk.tuke.kpi.BlockPuzzle.core.utilities.Coordinate;
import sk.tuke.kpi.BlockPuzzle.game.levels.GameLevel;

import java.util.regex.Pattern;

public class Parser {
    private static final Pattern GAME_LEVEL_PATTERN = Pattern.compile("^\\s*(\\d)\\s*$");
    private static final Pattern GAME_PLAY_PATTERN = Pattern.compile("^\\s*(\\d+)\\s*$");
    private static final Pattern LEAVE_COMMENT_OR_RATING_PATTERN = Pattern.compile("^\\s*(\\d+)\\s*$");
    private static final Pattern PROCEED_PLAYING_PATTERN = Pattern.compile("^\\s*(\\d+)\\s*$");
    private static final Pattern PLACE_BLOCK_PATTERN = Pattern.compile("^\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$");
    private static final Pattern REMOVE_BLOCK_PATTERN = Pattern.compile("^\\s*(\\d+)\\s+(\\d+)\\s*$");

    public static GameLevel parseGameLevelInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return GameLevel.INVALID;
        }

        var gameLevelMatcher = GAME_LEVEL_PATTERN.matcher(input);
        if (gameLevelMatcher.find()) {
            try {
                var level = Integer.parseInt(gameLevelMatcher.group(1));
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

        var gameLevelMatcher = GAME_PLAY_PATTERN.matcher(input);
        if (gameLevelMatcher.find()) {
            try {
                var level = Integer.parseInt(gameLevelMatcher.group(1));
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

        var removeBlockMatcher = REMOVE_BLOCK_PATTERN.matcher(input);
        if (removeBlockMatcher.find()) {
            try {
                var xCoordinate = Integer.parseInt(removeBlockMatcher.group(1));
                var yCoordinate = Integer.parseInt(removeBlockMatcher.group(2));
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

        var removeBlockMatcher = PLACE_BLOCK_PATTERN.matcher(input);
        if (removeBlockMatcher.find()) {
            try {
                var blockIndex = Integer.parseInt(removeBlockMatcher.group(1));
                var xCoordinate = Integer.parseInt(removeBlockMatcher.group(2));
                var yCoordinate = Integer.parseInt(removeBlockMatcher.group(3));
                return new PlaceBlockInput(blockIndex, new Coordinate(xCoordinate, yCoordinate));
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return null;
    }

    public static ProceedInput proceedInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return ProceedInput.INVALID;
        }

        var continuePlayingMatcher = PROCEED_PLAYING_PATTERN.matcher(input);
        if (continuePlayingMatcher.find()) {
            try {
                var choice = Integer.parseInt(continuePlayingMatcher.group(1));
                return ProceedInput.getProceedPlayingChoice(choice);
            } catch (NumberFormatException e) {
                return ProceedInput.INVALID;
            }
        }

        return ProceedInput.INVALID;
    }

    public static LeaveCommentOrRatingInput leaveCommentOrRatingInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return LeaveCommentOrRatingInput.INVALID;
        }

        var leaveMatcher = LEAVE_COMMENT_OR_RATING_PATTERN.matcher(input);
        if (leaveMatcher.find()) {
            try {
                var choice = Integer.parseInt(leaveMatcher.group(1));
                return LeaveCommentOrRatingInput.getLeaveCommentOrRatingInput(choice);
            } catch (NumberFormatException e) {
                return LeaveCommentOrRatingInput.INVALID;
            }
        }

        return LeaveCommentOrRatingInput.INVALID;
    }
}
