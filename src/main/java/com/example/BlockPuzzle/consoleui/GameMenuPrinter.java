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
    private static final int BLOCK_IN_ROW = 3;

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

        addBoardNumbersToString(boardWidth);
        for (int i = 0, n = 0; i < boardWidth || n < blocks.size(); i++) {
            if (blockRowIndex == BLOCK_TIMES_PRINT) {
                addBoardRowsToString(board, i);
                blockNumber += BLOCK_IN_ROW;
                blockRowIndex = 0;
            } else {
                for (int j = 1; j <= 3; j++) {
                    addBoardRowAndBlocksToString(board, blocks, i, blockNumber, blockRowIndex, j);
                }
                blockRowIndex++;
                if(blockRowIndex % 3 == 0) {
                    n += BLOCK_IN_ROW;
                }
            }
        }

        System.out.println(boardAndBlocksString);
    }

    private void addBoardRowsToString(Board board, int rowIndex) {
        for (int j = 1; j <= 3; j++) {
            if(rowIndex < board.getBoardShape().getBlockWidth()) {
                addBoardRowToString(board, rowIndex, j);
            } else {
                appendEmptyTile();
            }
            boardAndBlocksString.append('\n');
        }
    }

    private void addBoardRowToString(Board board, int rowIndex, int printTimeNumber) {
        var tiles = board.getBoardShape().getTiles();
        for (int i = 0; i < tiles.length; i++) {
            if(rowIndex < tiles.length) {
                appendColoredTile(tiles[rowIndex][i].getTileColor(), (printTimeNumber == 2) ? BLOCK_INSIDE : BLOCK_OUTSIDE);
            } else {
                appendEmptyTile();

            }
        }
        if(rowIndex < tiles.length) {
            boardAndBlocksString.append((printTimeNumber == 2) ? " " + (rowIndex + 1) : "  ");
        } else {
            boardAndBlocksString.append("  ");
        }
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
        System.out.println("\n" +
                "██████╗░██╗░░░░░░█████╗░░█████╗░██╗░░██╗██████╗░██╗░░░██╗███████╗███████╗██╗░░░░░███████╗\n" +
                "██╔══██╗██║░░░░░██╔══██╗██╔══██╗██║░██╔╝██╔══██╗██║░░░██║╚════██║╚════██║██║░░░░░██╔════╝\n" +
                "██████╦╝██║░░░░░██║░░██║██║░░╚═╝█████═╝░██████╔╝██║░░░██║░░███╔═╝░░███╔═╝██║░░░░░█████╗░░\n" +
                "██╔══██╗██║░░░░░██║░░██║██║░░██╗██╔═██╗░██╔═══╝░██║░░░██║██╔══╝░░██╔══╝░░██║░░░░░██╔══╝░░\n" +
                "██████╦╝███████╗╚█████╔╝╚█████╔╝██║░╚██╗██║░░░░░╚██████╔╝███████╗███████╗███████╗███████╗\n" +
                "╚═════╝░╚══════╝░╚════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░░╚═════╝░╚══════╝╚══════╝╚══════╝╚══════╝\n" +
                "Hello, welcome to the game!!!\n");
    }

    public void askPlayerForNextMove() {
        System.out.println("What would you like to do?");
        System.out.println("1. Place a block");
        System.out.println("2. Remove a block");
        System.out.println("3. Surrender");
        System.out.println("Enter your choice (1, 2, or 3): ");
    }

    public void askOfGameLevel() {
        System.out.println("Please choose the game level:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("4. Daily Puzzle");
        System.out.println("Enter your choice (1, 2, 3 or 4): ");
    }

    public void askWhatAndWherePlaceBlock() {
        System.out.println("Please choose the block you want to place and write X and Y coordinates where to place that block");
        System.out.println("Enter your choice in the form B X Y (B, X and Y are numbers): ");
    }

    public void askWhereRemoveBlock() {
        System.out.println("Please write X and Y coordinates to remove block from board");
        System.out.println("Enter your choice in the form X Y (X and Y are numbers): ");
    }

    public void reportPlayerAboutBadInput() {
        System.out.println("Sorry, you inputted incorrect coordinates\n");
    }

    public void askToProceed() {
        System.out.println("Shall we keep playing?");
        System.out.println("Type '1' to continue or '2' to exit.");
    }

    public void printCongratulations() {
        System.out.println("Congratulations!");
        System.out.println("You've completed the level successfully.\n");
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
