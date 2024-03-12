package com.example.BlockPuzzle.core.game;

import com.example.BlockPuzzle.consoleui.GameMenuPrinter;
import com.example.BlockPuzzle.core.board.Block;
import com.example.BlockPuzzle.core.board.Board;
import com.example.BlockPuzzle.core.exeptions.BlockNotFoundException;
import com.example.BlockPuzzle.core.exeptions.InvalidPlacementException;
import com.example.BlockPuzzle.core.game.levels.*;

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
            var level = levelFactory.createLevel(scanner.nextInt());

            if (level != null){
                var board = level.generateBoard();
                var blocks = level.generateBlocks();
                playGame(board, blocks);
            }

        }
    }

    private void playGame(Board board, List<Block> blocks) {
        while(!board.isBoardFolded()) {
            gameMenuPrinter.printBoardAndBlocks(board, blocks);
            gameMenuPrinter.askPlayerForNextMove();
            var playerChoice = scanner.nextInt();
            switch (playerChoice){
                case 1:
                    handlePlaceBlock(board, blocks);
                    break;
                case 2:
                    handleRemoveBlock(board, blocks);
                    break;
                case 3:
                    return;
            }
        }
    }

    private void handlePlaceBlock(Board board, List<Block> blocks) {
        gameMenuPrinter.askWhatAndWherePlaceBlock();
        var blockIndex = scanner.nextInt();
        var xCoordinate = scanner.nextInt();
        var yCoordinate = scanner.nextInt();

        try{
            board.placeBlock(blocks.get(blockIndex - 1), xCoordinate - 2, yCoordinate - 2);
            blocks.remove(blocks.get(blockIndex - 1));
        } catch (InvalidPlacementException e) {
            gameMenuPrinter.reportPlayerAboutBadInput();
        }
    }

    public void handleRemoveBlock(Board board, List<Block> blocks) {
        var xCoordinate = scanner.nextInt();
        var yCoordinate = scanner.nextInt();

        try{
            var block = board.removeBlock(xCoordinate - 2, yCoordinate - 2);
            blocks.add(block);
        } catch (BlockNotFoundException e) {
            gameMenuPrinter.reportPlayerAboutBadInput();
        }
    }
}
