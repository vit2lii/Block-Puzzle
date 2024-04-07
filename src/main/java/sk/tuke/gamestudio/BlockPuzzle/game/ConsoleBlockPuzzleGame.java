package sk.tuke.gamestudio.BlockPuzzle.game;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.BlockPuzzle.consoleui.GameMenuPrinter;
import sk.tuke.gamestudio.BlockPuzzle.core.board.Block;
import sk.tuke.gamestudio.BlockPuzzle.core.board.Board;
import sk.tuke.gamestudio.BlockPuzzle.game.levels.LevelFactory;
import sk.tuke.gamestudio.BlockPuzzle.service.CommentService;
import sk.tuke.gamestudio.BlockPuzzle.service.RatingService;
import sk.tuke.gamestudio.BlockPuzzle.service.ScoreService;
import sk.tuke.gamestudio.BlockPuzzle.consoleui.BoardPrinter;
import sk.tuke.gamestudio.BlockPuzzle.core.exeptions.BlockNotFoundException;
import sk.tuke.gamestudio.BlockPuzzle.core.exeptions.InvalidPlacementException;
import sk.tuke.gamestudio.BlockPuzzle.game.levels.GameLevel;
import sk.tuke.gamestudio.BlockPuzzle.parser.GamePlayType;
import sk.tuke.gamestudio.BlockPuzzle.parser.Parser;
import sk.tuke.gamestudio.BlockPuzzle.parser.YesNoInput;
import sk.tuke.gamestudio.BlockPuzzle.players.Player;
import sk.tuke.gamestudio.BlockPuzzle.service.jpa.DataBaseWriter;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Scanner;

public class ConsoleBlockPuzzleGame {
    private final static String GAME_NAME = "Block Puzzle";
    private final Scanner scanner;
    private final GameMenuPrinter gameMenuPrinter;
    private final BoardPrinter boardPrinter;
    private DataBaseWriter dataBaseWriter;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;

    public ConsoleBlockPuzzleGame() {
        this.scanner = new Scanner(System.in);
        this.gameMenuPrinter = new GameMenuPrinter();
        this.boardPrinter = new BoardPrinter();
    }

    @PostConstruct
    private void initializeDataBaseWriter() {
        dataBaseWriter = new DataBaseWriter(scoreService, commentService, ratingService);
    }

    public void startGame() {
        gameMenuPrinter.printStartGameScreen();
        gameMenuPrinter.printAboutGame();
        gameMenuPrinter.printHallOfFame(scoreService.getTopScores(GAME_NAME));
        gameMenuPrinter.printAverageRating(ratingService.getAverageRating(GAME_NAME));
        gameMenuPrinter.printComments(commentService.getComments(GAME_NAME));

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

            var proceedPlayingChoice = askToProceed();
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
        dataBaseWriter.setScore(player, player.getScore() + 100);
    }

    private Player createPlayer() {
        final var nickname = getPlayerNickname();
        return new Player(nickname, GAME_NAME);
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
        if (placeBlockInput == null || placeBlockInput.getBlockIndex() < 1 || placeBlockInput.getBlockIndex() > blocks.size()) {
            gameMenuPrinter.reportPlayerAboutBadInput();
            return;
        }

        try {
            board.placeBlock(blocks.get(placeBlockInput.getBlockIndex() - 1), placeBlockInput.getCoordinate().getY() - 1, placeBlockInput.getCoordinate().getX() - 1);
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
        YesNoInput choice;
        while ((choice = Parser.yesNoInput(scanner.nextLine())) == YesNoInput.INVALID) {
            gameMenuPrinter.reportPlayerAboutBadInput();
            gameMenuPrinter.askToProceed();
        }

        return choice;
    }

    private void leaveCommentAndRating(Player player) {
        leaveComment(player);
        leaveRating(player);
    }

    private void leaveComment(Player player) {
        gameMenuPrinter.askLeaveComment();
        YesNoInput choice;
        while ((choice = Parser.yesNoInput(scanner.nextLine())) == YesNoInput.INVALID) {
            gameMenuPrinter.reportPlayerAboutBadInput();
            gameMenuPrinter.askLeaveComment();
        }

        if (choice == YesNoInput.YES) {
            gameMenuPrinter.printCommentAdding();
            final var input = scanner.nextLine();
            if (input != null && !input.isEmpty() && !input.isBlank() && input.length() <= 64) {
                dataBaseWriter.addComment(player, input);
            }
        }
    }

    private void leaveRating(Player player) {
        gameMenuPrinter.askLeaveRating();
        YesNoInput choice;
        while ((choice = Parser.yesNoInput(scanner.nextLine())) == YesNoInput.INVALID) {
            gameMenuPrinter.reportPlayerAboutBadInput();
            gameMenuPrinter.askLeaveRating();
        }

        if (choice == YesNoInput.YES) {
            gameMenuPrinter.printRatingAdding();
            final var input = scanner.nextLine();
            if (input != null && !input.isEmpty() && !input.isBlank()) {
                dataBaseWriter.setRating(player, input);
            }
        }
    }
}
