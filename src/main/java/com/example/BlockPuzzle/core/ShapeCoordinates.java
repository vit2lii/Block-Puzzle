package com.example.BlockPuzzle.core;

import lombok.Data;

@Data
public class ShapeCoordinates {
    private int x;
    private int y;
    private Block block;

    public ShapeCoordinates(int x, int y, Block block) {
        this.x = x;
        this.y = y;
        this.block = block;
    }

    public boolean intersectsWithBoard(Block boardArea) {
        for (int rowIndex = 0; rowIndex < block.getRows(); rowIndex++) {
            for (int colIndex = 0; colIndex < block.getColumns(); colIndex++) {
                if (isTileOccupied(block, rowIndex, colIndex) &&
                        isTileOccupied(boardArea, x + rowIndex, y + colIndex)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isTileOccupied(Block area, int rowIndex, int colIndex) {
        return area.getTiles()[rowIndex][colIndex].getTileState() != TileState.EMPTY;
    }
}
