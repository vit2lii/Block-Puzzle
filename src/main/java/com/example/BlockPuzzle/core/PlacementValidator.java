package com.example.BlockPuzzle.core;

import com.example.BlockPuzzle.core.board.Block;

public class PlacementValidator {
    public static boolean isValidPlacement(Block boardShape, Block blockToPlace, Coordinate blockToPlaceCoordinates) {
        return !isOutOfBounds(boardShape, blockToPlaceCoordinates) && !intersectsWithBoard(boardShape, blockToPlace, blockToPlaceCoordinates);
    }

    public static boolean isOutOfBounds(Block boardShape, Coordinate blockToPlaceCoordinates) {
        return blockToPlaceCoordinates.getX() < 0
                || blockToPlaceCoordinates.getX() + Block.BLOCK_SIZE - 1 > boardShape.getRowsQnt() - 1
                || blockToPlaceCoordinates.getY() < 0
                || blockToPlaceCoordinates.getY() + Block.BLOCK_SIZE - 1 > boardShape.getColumnsQnt() - 1;
    }

    public static boolean intersectsWithBoard(Block boardShape, Block block, Coordinate blockCoordinate) {
        for (int rowIndex = 0; rowIndex < block.getRowsQnt(); rowIndex++) {
            for (int colIndex = 0; colIndex < block.getColumnsQnt(); colIndex++) {
                if (isTileOccupied(block, rowIndex, colIndex) &&
                        isTileOccupied(boardShape, blockCoordinate.getX() + rowIndex, blockCoordinate.getY() + colIndex)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isTileOccupied(Block area, int rowIndex, int colIndex) {
        return !area.getTiles()[rowIndex][colIndex].isEmptyTile();
    }
}