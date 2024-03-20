package sk.tuke.kpi.BlockPuzzle.consoleui;

import sk.tuke.kpi.BlockPuzzle.core.board.Block;
import sk.tuke.kpi.BlockPuzzle.core.board.Board;
import sk.tuke.kpi.BlockPuzzle.core.board.Color;

import java.util.ArrayList;
import java.util.List;

public class BoardPrinter {
    private static final String BLOCK_OUTSIDE = "               ";
    private static final String BLOCK_INSIDE = "     •         ";
    private static final String BIG_SPACE = "         ";
    private static final int BLOCK_TIMES_PRINT = 3;
    private static final int BLOCK_IN_ROW = 3;

    private final StringBuilder boardAndBlocksString;
    private final ColorMap backGroundColorMap;

    public BoardPrinter() {
        this.boardAndBlocksString = new StringBuilder();
        this.backGroundColorMap = new ColorMap();
    }

    public void printBoardAndBlocks(Board board, List<Block> blocks) {
        boardAndBlocksString.setLength(0);

        int boardWidth = board.getBoardShape().getWidth();
        int blockNumber = 0;
        int blockRowIndex = 0;

        var newBlock = new ArrayList<Block>();
        for(var block: blocks) {
            newBlock.add(block.createBlockInStandardSize());
        }

        addBoardNumbersToString(boardWidth);
        for (int i = 0, n = 0; i < boardWidth || n < newBlock.size(); i++) {
            if (blockRowIndex == BLOCK_TIMES_PRINT) {
                addBoardRowsToString(board, i);
                blockNumber += BLOCK_IN_ROW;
                blockRowIndex = 0;
            } else {
                for (int j = 1; j <= 3; j++) {
                    addBoardRowAndBlocksToString(board, newBlock, i, blockNumber, blockRowIndex, j);
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
            if(rowIndex < board.getBoardShape().getWidth()) {
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

        for (int colIndex = 0; colIndex < Block.STANDARD_BLOCK_SIZE; colIndex++) {
            var tile = block.getTiles()[rowIndex][colIndex];
            if (tile.isEmptyTile()) {
                appendEmptyTile();
            } else {
                appendColoredTile(tile.getTileColor(), tile.isMovableTile() ? tileType : BLOCK_OUTSIDE);
            }
        }
    }

    private void appendColoredTile(Color color, String tile) {
        boardAndBlocksString.append(backGroundColorMap.getBackgroundColor(color)).append(tile).append(backGroundColorMap.getDefaultColor());
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
}
