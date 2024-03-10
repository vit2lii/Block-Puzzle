package com.example.BlockPuzzle.consoleui;

import com.example.BlockPuzzle.core.board.Block;
import com.example.BlockPuzzle.core.board.Board;
import com.example.BlockPuzzle.core.board.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMenuPrinter {
    private static final String BLOCK = "\033[100;97m               \033[0m";
    private static final String BLOCK_INSIDE = "\033[100;97m     •         \033[0m";
    private static final String SPACE = "    ";
    private final StringBuilder boardAndBlocksString;
    private final Map<Color, String> colorsBackgroundString;

    public GameMenuPrinter() {
        boardAndBlocksString = new StringBuilder();
        colorsBackgroundString = new HashMap<>();
        setMapWithValues();
    }

    public void printBoardAndBlocks(Board board, List<Block> blocks) {
        var boardWidth = board.getBoardShape().getBlockWidth();

        for (int i = 0; i < boardWidth; i++) {
            int blockRowIndex = 0;
            int blockCount = 0;

            addRowToString(boardWidth);
            //addBlockToString(blocks.get(blockCount), blockRowIndex);

            addRowWithNumberToString(boardWidth, i + 1);
            addRowToString(boardWidth);
        }
        addBoardNumbersToString(boardWidth);

        System.out.println(boardAndBlocksString);
    }

    private void addBlockToString(Block block, int rowIndex) {
        if (block.getTiles()[rowIndex][0].isMovableTile()) {
            boardAndBlocksString.append(colorsBackgroundString.get(block.getTiles()[rowIndex][0].getTileColor())).append("               \033[0m");
        }
        if (block.getTiles()[rowIndex][1].isMovableTile()) {
            boardAndBlocksString.append(colorsBackgroundString.get(block.getTiles()[rowIndex][0].getTileColor())).append("               \033[0m");
        }
        if (block.getTiles()[rowIndex][2].isMovableTile()) {
            boardAndBlocksString.append(colorsBackgroundString.get(block.getTiles()[rowIndex][0].getTileColor())).append("               \033[0m");
        }
    }

    private void addRowToString(int boardWidth) {
        boardAndBlocksString.append(BLOCK.repeat(Math.max(0, boardWidth)));
        boardAndBlocksString.append("  \n");
    }

    private void addRowWithNumberToString(int boardWidth, int number) {
        boardAndBlocksString.append(BLOCK_INSIDE.repeat(Math.max(0, boardWidth)));
        boardAndBlocksString.append(" ").append(number).append('\n');
    }

    private void addBoardNumbersToString(int boardWidth) {
        boardAndBlocksString.append(' ').append(SPACE);
        for (int i = 1; i <= boardWidth; i++) {
            boardAndBlocksString.append(i).append(SPACE).append(' ').append(SPACE).append(' ').append(SPACE);
        }
    }

    public void askPlayerForNextMove() {
        System.out.println("What would you like to do?");
        System.out.println("1. Place a block");
        System.out.println("2. Remove a block");
        System.out.println("3. Surrender");
        System.out.println("Enter your choice (1, 2, or 3): ");
    }

    public void printStartGameScreen() {
        System.out.println();
    }

    public void askOfGameLevel() {
        System.out.println("Please choose the game level:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("4. Tutorial");
        System.out.println("Enter your choice (1, 2, 3 or 4): ");
    }

    private void setMapWithValues() {
        colorsBackgroundString.put(Color.BLACK, "\033[100;30m");
        colorsBackgroundString.put(Color.RED, "\033[100;30m");
        colorsBackgroundString.put(Color.GREEN, "\033[100;30m");
        colorsBackgroundString.put(Color.YELLOW, "\033[100;30m");
        colorsBackgroundString.put(Color.BLUE, "\033[100;30m");
        colorsBackgroundString.put(Color.MAGENTA, "\033[100;30m");
        colorsBackgroundString.put(Color.CYAN, "\033[100;30m");
        colorsBackgroundString.put(Color.WHITE, "\033[100;30m");
        colorsBackgroundString.put(Color.GRAY, "\033[100;30m");
        colorsBackgroundString.put(Color.BRIGHT_RED, "\033[100;30m");
        colorsBackgroundString.put(Color.BRIGHT_GREEN, "\033[100;30m");
        colorsBackgroundString.put(Color.BRIGHT_YELLOW, "\033[100;30m");
        colorsBackgroundString.put(Color.BRIGHT_BLUE, "\033[100;30m");
        colorsBackgroundString.put(Color.BRIGHT_CYAN, "\033[100;30m");
    }
}
