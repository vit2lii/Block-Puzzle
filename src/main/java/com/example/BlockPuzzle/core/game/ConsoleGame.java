package com.example.BlockPuzzle.core.game;

import com.example.BlockPuzzle.consoleui.GameMenuPrinter;
import com.example.BlockPuzzle.core.board.Block;
import com.example.BlockPuzzle.core.board.Board;
import com.example.BlockPuzzle.core.exeptions.BlockNotFoundException;
import com.example.BlockPuzzle.core.exeptions.InvalidPlacementException;
import com.example.BlockPuzzle.core.game.levels.LevelFactory;

import java.util.List;
import java.util.Scanner;

public class ConsoleGame {
    private final Scanner scanner = new Scanner(System.in);
    private final GameMenuPrinter gameMenuPrinter = new GameMenuPrinter();
    private final LevelFactory levelFactory = new LevelFactory();

    public static void main(String[] args) {
        var consoleGame = new ConsoleGame();
        consoleGame.startGame();
    }

    public void startGame() {
        gameMenuPrinter.printStartGameScreen();

        while (true) {
            gameMenuPrinter.askOfGameLevel();
            var levelInput = Parser.parseGameLevelInput(scanner.nextLine());
            var level = levelFactory.createLevel(levelInput.getLevelValue());

            if (level != null) {
                var board = level.generateBoard();
                var blocks = level.generateBlocks();
                playGame(board, blocks);
            }
        }
    }

    private void playGame(Board board, List<Block> blocks) {
        gameMenuPrinter.printBoardAndBlocks(board, blocks);

        while (!board.isBoardFolded()) {
            gameMenuPrinter.askPlayerForNextMove();
            var gamePlayType = Parser.parseGamePlayTypeInput(scanner.nextLine());

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

            gameMenuPrinter.printBoardAndBlocks(board, blocks);
        }
    }

    private void handlePlaceBlock(Board board, List<Block> blocks) {
        gameMenuPrinter.askWhatAndWherePlaceBlock();

        var placeBlockInput = Parser.placeBlockInput(scanner.nextLine());
        if(placeBlockInput == null) {
            return;
        }

        try {
            board.placeBlock(blocks.get(placeBlockInput.getBlockIndex() - 1), placeBlockInput.getCoordinate().getX() - 2, placeBlockInput.getCoordinate().getY() - 2);
            blocks.remove(blocks.get(placeBlockInput.getBlockIndex() - 1));
        } catch (InvalidPlacementException e) {
            gameMenuPrinter.reportPlayerAboutBadInput();
        }
    }

    private void handleRemoveBlock(Board board, List<Block> blocks) {
        gameMenuPrinter.askWhereRemoveBlock();

        var removeBlockCoordinate = Parser.removeBlockInput(scanner.nextLine());
        if(removeBlockCoordinate == null) {
            return;
        }

        try {
            var block = board.removeBlock(removeBlockCoordinate.getX() - 2, removeBlockCoordinate.getY() - 2);
            blocks.add(block);
        } catch (BlockNotFoundException e) {
            gameMenuPrinter.reportPlayerAboutBadInput();
        }
    }
}
