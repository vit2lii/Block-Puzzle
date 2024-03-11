package com.example.BlockPuzzle.core.game;

import com.example.BlockPuzzle.consoleui.GameMenuPrinter;
import com.example.BlockPuzzle.core.board.*;
import com.example.BlockPuzzle.core.game.levels.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleGame {
    private final Scanner scanner = new Scanner(System.in);
    private final GameMenuPrinter gameMenuPrinter = new GameMenuPrinter();
    private final LevelFactory levelFactory = new LevelFactory();

    public static void main(String[] args) {
        var board = new Board(new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)}
                }

        ));

        var block1 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BLUE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)}
                });
        var block2 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.GREEN), new Tile(TileState.MOVABLE, Color.GREEN)},
                        {new Tile(TileState.MOVABLE, Color.GREEN), new Tile(TileState.MOVABLE, Color.GREEN), new Tile(TileState.MOVABLE, Color.GREEN)},
                        {new Tile(TileState.MOVABLE, Color.GREEN), new Tile(TileState.MOVABLE, Color.GREEN), new Tile(TileState.MOVABLE, Color.GREEN)}
                });
        var block3 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.RED)},
                        {new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED)},
                        {new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.RED)}
                });
        var block4 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.EMPTY, Color.GREEN), new Tile(TileState.MOVABLE, Color.MAGENTA)},
                        {new Tile(TileState.EMPTY, Color.GREEN), new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.EMPTY, Color.GREEN)},
                        {new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.EMPTY, Color.GREEN), new Tile(TileState.MOVABLE, Color.MAGENTA)}
                });
        var block5 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.EMPTY, Color.GREEN)},
                        {new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW)},
                        {new Tile(TileState.EMPTY, Color.GREEN), new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.EMPTY, Color.GREEN)}
                });
        var gameMenuPrinter = new GameMenuPrinter();
        List<Block> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);
        gameMenuPrinter.printBoardAndBlocks(board, blocks);
    }

    public void startGame() {
        while (true) {
            gameMenuPrinter.printStartGameScreen();
            gameMenuPrinter.askOfGameLevel();
            var level = levelFactory.createLevel(scanner.nextInt());

            if (level != null){
                playGame(level);
            }
        }
    }

    private void playGame(Level level) {
        var board = level.generateBoard();
        var block = level.generateBlocks();
    }
}
