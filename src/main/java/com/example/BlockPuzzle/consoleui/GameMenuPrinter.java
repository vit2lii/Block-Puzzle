package com.example.BlockPuzzle.consoleui;

import com.example.BlockPuzzle.core.board.Block;
import com.example.BlockPuzzle.core.board.Board;
import com.example.BlockPuzzle.core.board.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMenuPrinter {
    private static final String BLOCK_OUTSIDE = "               ";
    private static final String BLOCK_INSIDE = "     •         ";
    private static final String SPACE = "    ";
    private static final String BIG_SPACE = "         ";

    private final StringBuilder boardAndBlocksString;
    private final Map<Color, String> colorsBackgroundString;

    public GameMenuPrinter() {
        boardAndBlocksString = new StringBuilder();
        colorsBackgroundString = initializeColorMap();
    }

    public void printBoardAndBlocks(Board board, List<Block> blocks) {
        int boardWidth = board.getBoardShape().getBlockWidth();

        int blockNumber = 0;
        int blockCount = blocks.size();
        int blockRowIndex = 0;
        for (int i = 0; i < boardWidth; i++) {
            if (blockRowIndex == 3) {
                blockNumber += 3;
                blockRowIndex = 0;
                addRowToString(boardWidth);
                addRowWithNumberToString(boardWidth, i + 1);
                addRowToString(boardWidth);
            } else {
                addRowToString(boardWidth);
                printBlockRow(blocks, blockNumber, blockCount, blockRowIndex);

                addRowWithNumberToString(boardWidth, i + 1);

                printBlockRow(blocks, blockNumber, blockCount, blockRowIndex);

                addRowToString(boardWidth);

                printBlockRow(blocks, blockNumber, blockCount, blockRowIndex);

                blockRowIndex++;
            }

        }
        addBoardNumbersToString(boardWidth);

        System.out.println(boardAndBlocksString);
    }

    private void printBlockRow(List<Block> blocks, int blockNumber, int blockCount, int blockRowIndex) {
        for (int j = 0; j < 3; j++) {
            if (blockNumber < blockCount) {
                addBlockToString(blocks.get(blockNumber++), blockRowIndex);
            }
        }
    }

    private void addBlockToString(Block block, int rowIndex) {
        boardAndBlocksString.append(BIG_SPACE);

        for (int colIndex = 0; colIndex < Block.BLOCK_SIZE; colIndex++) {
            var tile = block.getTiles()[rowIndex][colIndex];
            var color = tile.getTileColor();
            if (tile.isMovableTile()) {
                appendColoredTile(color);
            } else {
                appendEmptyTile();
            }
        }
    }

    private void appendColoredTile(Color color) {
        boardAndBlocksString.append(colorsBackgroundString.get(color)).append(BLOCK_OUTSIDE).append("\033[0m");
    }

    private void appendEmptyTile() {
        boardAndBlocksString.append(BLOCK_OUTSIDE);
    }

    private void addRowToString(int boardWidth) {
        boardAndBlocksString.append('\n');
        for(int i = 0; i < boardWidth; i++) {
            boardAndBlocksString.append(colorsBackgroundString.get(Color.GRAY)).append(BLOCK_OUTSIDE).append("\033[0m");
        }
        boardAndBlocksString.append("  ");
    }

    private void addRowWithNumberToString(int boardWidth, int number) {
        boardAndBlocksString.append('\n');
        for(int i = 0; i < boardWidth; i++) {
            boardAndBlocksString.append(colorsBackgroundString.get(Color.GRAY)).append(BLOCK_INSIDE).append("\033[0m");
        }
        boardAndBlocksString.append(" ").append(number);
    }

    private void addBoardNumbersToString(int boardWidth) {
        boardAndBlocksString.append('\n').append(' ').append(SPACE);
        for (int i = 1; i <= boardWidth; i++) {
            boardAndBlocksString.append(i).append(SPACE).append(' ').append(SPACE).append(' ').append(SPACE);
        }
    }

    public void printStartGameScreen() {

    }

    public void askPlayerForNextMove() {
        printMessage("What would you like to do?");
        printMessage("1. Place a block");
        printMessage("2. Remove a block");
        printMessage("3. Surrender");
        printMessage("Enter your choice (1, 2, or 3): ");
    }

    public void askOfGameLevel() {
        printMessage("Please choose the game level:");
        printMessage("1. Easy");
        printMessage("2. Medium");
        printMessage("3. Hard");
        printMessage("4. Tutorial");
        printMessage("Enter your choice (1, 2, 3 or 4): ");
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private Map<Color, String> initializeColorMap() {
        Map<Color, String> colorsBackgroundString = new HashMap<>();
        colorsBackgroundString.put(Color.BLACK, "\033[40;97m");
        colorsBackgroundString.put(Color.RED, "\033[41;97m");
        colorsBackgroundString.put(Color.GREEN, "\033[42;97m");
        colorsBackgroundString.put(Color.YELLOW, "\033[43;97m");
        colorsBackgroundString.put(Color.BLUE, "\033[44;97m");
        colorsBackgroundString.put(Color.MAGENTA, "\033[45;97m");
        colorsBackgroundString.put(Color.CYAN, "\033[46;97m");
        colorsBackgroundString.put(Color.WHITE, "\033[47;97m");
        colorsBackgroundString.put(Color.GRAY, "\033[100;97m");
        colorsBackgroundString.put(Color.BRIGHT_RED, "\033[101;97m");
        colorsBackgroundString.put(Color.BRIGHT_GREEN, "\033[102;97m");
        colorsBackgroundString.put(Color.BRIGHT_YELLOW, "\033[103;97m");
        colorsBackgroundString.put(Color.BRIGHT_BLUE, "\033[104;97m");
        colorsBackgroundString.put(Color.BRIGHT_MAGENTA, "\033[105;97m");
        colorsBackgroundString.put(Color.BRIGHT_CYAN, "\033[106;97m");
        return colorsBackgroundString;
    }
}
