package com.example.BlockPuzzle.core.board;

import com.example.BlockPuzzle.core.utilities.Coordinate;

public class PlacementValidator {
    public static boolean isValidPlacement(Block boardShape, Block blockToPlace, Coordinate blockToPlaceCoordinates) {
        return !isOutOfBounds(boardShape, blockToPlaceCoordinates) && !intersectsWithBoard(boardShape, blockToPlace, blockToPlaceCoordinates);
    }

    private static boolean isOutOfBounds(Block boardShape, Coordinate blockToPlaceCoordinates) {
        return blockToPlaceCoordinates.getX() < 0
                || blockToPlaceCoordinates.getX() + Block.BLOCK_SIZE - 1 > boardShape.getBlockWidth() - 1
                || blockToPlaceCoordinates.getY() < 0
                || blockToPlaceCoordinates.getY() + Block.BLOCK_SIZE - 1 > boardShape.getBlockWidth() - 1;
    }

    private static boolean intersectsWithBoard(Block boardShape, Block block, Coordinate blockCoordinate) {
        for (int rowIndex = 0; rowIndex < block.getBlockWidth(); rowIndex++) {
            for (int colIndex = 0; colIndex < block.getBlockWidth(); colIndex++) {
                if (isTileOccupied(block, rowIndex, colIndex) && isTileOccupied(boardShape, blockCoordinate.getX() + rowIndex, blockCoordinate.getY() + colIndex)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isTileOccupied(Block board, int rowIndex, int colIndex) {
        return !board.getTiles()[rowIndex][colIndex].isEmptyTile();
    }
}