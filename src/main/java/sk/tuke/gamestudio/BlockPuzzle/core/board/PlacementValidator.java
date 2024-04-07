package sk.tuke.gamestudio.BlockPuzzle.core.board;

import sk.tuke.gamestudio.BlockPuzzle.core.utilities.Coordinate;

public class PlacementValidator {
    public static boolean isValidPlacement(Block boardShape, Block blockToPlace, Coordinate blockToPlaceCoordinates) {
        return !isOutOfBounds(boardShape, blockToPlace, blockToPlaceCoordinates) && !intersectsWithBoard(boardShape, blockToPlace, blockToPlaceCoordinates);
    }

    private static boolean isOutOfBounds(Block boardShape, Block blockToPlace, Coordinate blockToPlaceCoordinates) {
        return blockToPlaceCoordinates.getX() < 0
                || blockToPlaceCoordinates.getX() + blockToPlace.getHeight() - 1 > boardShape.getHeight() - 1
                || blockToPlaceCoordinates.getY() < 0
                || blockToPlaceCoordinates.getY() + blockToPlace.getWidth() - 1 > boardShape.getWidth() - 1;
    }

    private static boolean intersectsWithBoard(Block boardShape, Block block, Coordinate blockCoordinate) {
        for (int rowIndex = 0; rowIndex < block.getHeight(); rowIndex++) {
            for (int colIndex = 0; colIndex < block.getWidth(); colIndex++) {
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