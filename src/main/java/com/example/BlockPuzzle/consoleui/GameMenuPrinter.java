package com.example.BlockPuzzle.consoleui;

import com.example.BlockPuzzle.core.board.Block;
import com.example.BlockPuzzle.core.board.Board;
import com.example.BlockPuzzle.core.board.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMenuPrinter {
    private static final String BLOCK_OUTSIDE = "               ";
    private static final String BLOCK_INSIDE = "     •         ";
    private static final String BIG_SPACE = "         ";
    private static final int BLOCK_TIMES_PRINT = 3;
    private static final int BLOCK_IN_ROW = 4;

    private final StringBuilder boardAndBlocksString;
    private final Map<Color, String> colorsBackgroundString;

    public GameMenuPrinter() {
        boardAndBlocksString = new StringBuilder();
        colorsBackgroundString = initializeColorMap();
    }

    public void printBoardAndBlocks(Board board, List<Block> blocks) {
        boardAndBlocksString.setLength(0);

        int boardWidth = board.getBoardShape().getBlockWidth();
        int blockNumber = 0;
        int blockRowIndex = 0;

        for (int i = 0; i < boardWidth; i++) {
            if (blockRowIndex == BLOCK_TIMES_PRINT) {
                addBoardRowsToString(board, i);
                blockNumber += BLOCK_IN_ROW;
                blockRowIndex = 0;
            } else {
                for (int j = 1; j <= 3; j++) {
                    addBoardRowAndBlocksToString(board, blocks, i, blockNumber, blockRowIndex, j);
                }
                blockRowIndex++;
            }
        }

        addBoardNumbersToString(boardWidth);
        System.out.println(boardAndBlocksString);
    }

    private void addBoardRowsToString(Board board, int rowIndex) {
        for (int j = 1; j <= 3; j++) {
            addBoardRowToString(board, rowIndex, j);
            boardAndBlocksString.append('\n');
        }
    }

    private void addBoardRowToString(Board board, int rowIndex, int printTimeNumber) {
        var tiles = board.getBoardShape().getTiles();
        for (int i = 0; i < tiles.length; i++) {
            appendColoredTile(tiles[rowIndex][i].getTileColor(), (printTimeNumber == 2) ? BLOCK_INSIDE : BLOCK_OUTSIDE);
        }

        boardAndBlocksString.append((printTimeNumber == 2) ? " " + (rowIndex + 1) : "  ");
    }

    private void addBoardRowAndBlocksToString(Board board, List<Block> blocks, int rowIndex, int blockNumber, int blockRowIndex, int printTimeNumber) {
        addBoardRowToString(board, rowIndex, printTimeNumber);
        addBlocksToString(blocks, blockNumber, blockRowIndex, printTimeNumber);
    }

    private void addBlocksToString(List<Block> blocks, int blockNumber, int blockRowIndex, int printTimeNumber) {
        var blocksSize = blocks.size();

        for (int j = 0; j < BLOCK_IN_ROW && blockNumber < blocksSize; j++) {
            appendBlockTiles(blocks.get(blockNumber++), blockRowIndex, (printTimeNumber == 2) ? BLOCK_INSIDE : BLOCK_OUTSIDE);
        }
        boardAndBlocksString.append('\n');
    }

    private void appendBlockTiles(Block block, int rowIndex, String tileType) {
        boardAndBlocksString.append(BIG_SPACE);

        for (int colIndex = 0; colIndex < Block.BLOCK_SIZE; colIndex++) {
            var tile = block.getTiles()[rowIndex][colIndex];
            if (tile.isEmptyTile()) {
                appendEmptyTile();
            } else {
                appendColoredTile(tile.getTileColor(), tile.isMovableTile() ? tileType : BLOCK_OUTSIDE);
            }
        }
    }

    private void appendColoredTile(Color color, String tile) {
        boardAndBlocksString.append(colorsBackgroundString.get(color)).append(tile).append("\033[0m");
    }

    private void appendEmptyTile() {
        boardAndBlocksString.append(BLOCK_OUTSIDE);
    }

    private void addBoardNumbersToString(int boardWidth) {
        for (int i = 1; i <= boardWidth; i++) {
            boardAndBlocksString.append("     ").append(i).append("         ");
        }

        boardAndBlocksString.append('\n');
    }

    public void printStartGameScreen() {
        // No implementation yet
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

    public void askWhatAndWherePlaceBlock() {
        printMessage("Please choose the block you want to place and write X and Y coordinate where to place that block:");
        printMessage("Enter your choice in the form B X Y (B, X and Y are numbers)");
    }

    public void reportPlayerAboutBadInput() {
        printMessage("Sorry, you inputted incorrect coordinates");
        printMessage("");
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
