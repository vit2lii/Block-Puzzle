package com.example.BlockPuzzle.core;

public class PlacementValidator {
    public static boolean isValidPlacement(Block boardShape, Block blockToPlace, Coordinate blockToPlaceCoordinates) {
        return !isOutOfBounds(boardShape, blockToPlaceCoordinates) && intersectsWithBoard(boardShape, blockToPlace, blockToPlaceCoordinates);
    }

    public static boolean isOutOfBounds(Block boardShape, Coordinate blockToPlaceCoordinates) {
        return blockToPlaceCoordinates.getX() < 0
                || blockToPlaceCoordinates.getX() + Block.BLOCK_SIZE > boardShape.getRows() - 1
                || blockToPlaceCoordinates.getY() < 0
                || blockToPlaceCoordinates.getY() + Block.BLOCK_SIZE > boardShape.getColumns() - 1;
    }

    public static boolean intersectsWithBoard(Block boardShape, Block block, Coordinate blockCoordinate) {
        for (int rowIndex = 0; rowIndex < block.getRows(); rowIndex++) {
            for (int colIndex = 0; colIndex < block.getColumns(); colIndex++) {
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