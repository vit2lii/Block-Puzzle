package sk.tuke.kpi.BlockPuzzle.game;

import sk.tuke.kpi.BlockPuzzle.consoleui.BoardPrinter;
import sk.tuke.kpi.BlockPuzzle.consoleui.GameMenuPrinter;
import sk.tuke.kpi.BlockPuzzle.core.board.Block;
import sk.tuke.kpi.BlockPuzzle.core.board.Board;
import sk.tuke.kpi.BlockPuzzle.core.exeptions.*;
import sk.tuke.kpi.BlockPuzzle.game.levels.GameLevel;
import sk.tuke.kpi.BlockPuzzle.game.levels.LevelFactory;
import sk.tuke.kpi.BlockPuzzle.gamestudio.service.CommentServiceJDBC;
import sk.tuke.kpi.BlockPuzzle.gamestudio.service.RatingServiceJDBC;
import sk.tuke.kpi.BlockPuzzle.gamestudio.service.ScoreServiceJDBC;
import sk.tuke.kpi.BlockPuzzle.parser.GamePlayType;
import sk.tuke.kpi.BlockPuzzle.parser.Parser;
import sk.tuke.kpi.BlockPuzzle.parser.YesNoInput;
import sk.tuke.kpi.BlockPuzzle.players.Player;

import java.util.List;
import java.util.Scanner;

public class ConsoleBlockPuzzleGame {
    private final static String GAME_NAME = "Block Puzzle";
    private final Scanner scanner;
    private final GameMenuPrinter gameMenuPrinter;
    private final BoardPrinter boardPrinter;

    public ConsoleBlockPuzzleGame() {
        this.scanner = new Scanner(System.in);
        this.gameMenuPrinter = new GameMenuPrinter();
        this.boardPrinter = new BoardPrinter();
    }

    public void startGame() {
        gameMenuPrinter.printStartGameScreen();
        gameMenuPrinter.printHallOfFame(new ScoreServiceJDBC().getTopScores(GAME_NAME));
        gameMenuPrinter.printAverageRating(new RatingServiceJDBC().getAverageRating(GAME_NAME));
        gameMenuPrinter.printComments(new CommentServiceJDBC().getComments(GAME_NAME));

        final var player = createPlayer();

        while (true) {
            final var levelType = askForGameLevel();
            if (levelType == GameLevel.INVALID) {
                gameMenuPrinter.reportPlayerAboutBadInput();
                continue;
            } else if (levelType == GameLevel.EXIT) {
                leaveCommentAndRating(player);
                return;
            }

            final var level = LevelFactory.createLevel(levelType);
            if (level != null) {
                final var board = level.generateBoard();
                final var blocks = level.generateBlocks();
                playGame(board, blocks, player);
            }

            gameMenuPrinter.printPlayerScore(player.getScore());

            final var proceedPlayingChoice = askToProceed();
            switch (proceedPlayingChoice) {
                case YES:
                    break;
                case NO:
                    leaveCommentAndRating(player);
                    return;
                default:
                    gameMenuPrinter.reportPlayerAboutBadInput();
            }
        }
    }

    private void playGame(Board board, List<Block> blocks, Player player) {
        boardPrinter.printBoardAndBlocks(board, blocks);

        while (!board.isBoardFolded()) {
            final var gamePlayType = askPlayerForNextMove();
            switch (gamePlayType) {
                case PLACE_BLOCK:
                    handlePlaceBlock(board, blocks);
                    break;
                case REMOVE_BLOCK:
                    handleRemoveBlock(board, blocks);
                    break;
                case SURRENDER:
                    return;
                default:
                    gameMenuPrinter.reportPlayerAboutBadInput();
            }
            boardPrinter.printBoardAndBlocks(board, blocks);
        }
        gameMenuPrinter.printCongratulations();
        player.setScore(GAME_NAME, player.getScore() + 100);
    }

    private Player createPlayer() {
        final var nickname = getPlayerNickname();
        return new Player(nickname);
    }

    private String getPlayerNickname() {
        String nickname;
        do {
            gameMenuPrinter.askPlayerNickname();
            nickname = scanner.nextLine();
        } while (nickname.isEmpty() || nickname.length() > 64 || nickname.isBlank());
        return nickname;
    }

    private GameLevel askForGameLevel() {
        gameMenuPrinter.askOfGameLevel();
        return Parser.parseGameLevelInput(scanner.nextLine());
    }

    private GamePlayType askPlayerForNextMove() {
        gameMenuPrinter.askPlayerForNextMove();
        return Parser.parseGamePlayTypeInput(scanner.nextLine());
    }

    private void handlePlaceBlock(Board board, List<Block> blocks) {
        gameMenuPrinter.askWhatAndWherePlaceBlock();

        final var placeBlockInput = Parser.placeBlockInput(scanner.nextLine());
        if (placeBlockInput == null || placeBlockInput.getBlockIndex() < 1 || placeBlockInput.getBlockIndex() > blocks.size()){
            gameMenuPrinter.reportPlayerAboutBadInput();
            return;
        }

        try {
            board.placeBlock(blocks.get(placeBlockInput.getBlockIndex() - 1), placeBlockInput.getCoordinate().getY() - 1, placeBlockInput.getCoordinate().getX() - 1) ;
            blocks.remove(blocks.get(placeBlockInput.getBlockIndex() - 1));
        } catch (InvalidPlacementException e) {
            gameMenuPrinter.reportPlayerAboutBadInput();
        }
    }

    private void handleRemoveBlock(Board board, List<Block> blocks) {
        gameMenuPrinter.askWhereRemoveBlock();

        final var removeBlockCoordinate = Parser.removeBlockInput(scanner.nextLine());
        if (removeBlockCoordinate == null) {
            gameMenuPrinter.reportPlayerAboutBadInput();
            return;
        }

        try {
            final var block = board.removeBlock(removeBlockCoordinate.getY() - 1, removeBlockCoordinate.getX() - 1);
            blocks.add(block);
        } catch (BlockNotFoundException e) {
            gameMenuPrinter.reportPlayerAboutBadInput();
        }
    }

    private YesNoInput askToProceed() {
        gameMenuPrinter.askToProceed();
        return Parser.yesNoInput(scanner.nextLine());
    }

    private void leaveCommentAndRating(Player player) {
        leaveComment(player);
        leaveRating(player);
    }

    private void leaveComment(Player player) {
        gameMenuPrinter.askLeaveComment();
        final var choice = Parser.yesNoInput(scanner.nextLine());
        if (choice == YesNoInput.YES) {
            gameMenuPrinter.printCommentAdding();
            final var input = scanner.nextLine();
            if (input != null && !input.isEmpty() && !input.isBlank() && input.length() <= 64) {
                player.addComment(GAME_NAME, input);
            }
        }
    }

    private void leaveRating(Player player) {
        gameMenuPrinter.askLeaveRating();
        final var choice = Parser.yesNoInput(scanner.nextLine());
        if (choice == YesNoInput.YES) {
            gameMenuPrinter.printRatingAdding();
            final var input = scanner.nextLine();
            if (input != null && !input.isEmpty() && !input.isBlank()) {
                player.setRating(GAME_NAME, input);
            }
        }
    }
}
