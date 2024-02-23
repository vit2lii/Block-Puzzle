package com.example.BlockPuzzle.core;

import lombok.Data;

@Data
public class Board {
    private Block boardShape;
    private boolean isFolded;

    public Board(Block boardShape) {
        this.boardShape = boardShape;
        isFolded = false;
    }

    public void placeBlock(ShapeCoordinates blockToPlaceCoordinates) {
        if (!isValidPlacement(blockToPlaceCoordinates)) {
            return;
        }

        applyChangesToTiles((rowIndex, colIndex) ->
                placeTileIfEmpty(blockToPlaceCoordinates, rowIndex, colIndex));
    }

    private void placeTileIfEmpty(ShapeCoordinates blockToPlaceCoordinates, int rowIndex, int colIndex) {
        var x = blockToPlaceCoordinates.getX() + rowIndex;
        var y = blockToPlaceCoordinates.getY() + colIndex;
        var tile = boardShape.getTiles()[x][y];

        if (tile.getTileState() == TileState.EMPTY) {
            boardShape.getTiles()[x][y] = blockToPlaceCoordinates.getBlock().getTiles()[rowIndex][colIndex];
        }
    }

    private void applyChangesToTiles(TileChanger tileChanger) {
        for (int rowIndex = 0; rowIndex < Block.BLOCK_SIZE; rowIndex++) {
            for (int colIndex = 0; colIndex < Block.BLOCK_SIZE; colIndex++) {
                tileChanger.changeTile(rowIndex, colIndex);
            }
        }
    }

    private boolean isValidPlacement(ShapeCoordinates blockToPlaceCoordinates) {
        return !isOutOfBounds(blockToPlaceCoordinates) && collideWithWallsAndBlocks(blockToPlaceCoordinates);
    }

    private boolean isOutOfBounds(ShapeCoordinates blockToPlaceCoordinates) {
        return blockToPlaceCoordinates.getX() < 0 ||
                blockToPlaceCoordinates.getX() + Block.BLOCK_SIZE > boardShape.getRows() - 1 ||
                blockToPlaceCoordinates.getY() < 0 ||
                blockToPlaceCoordinates.getY() + Block.BLOCK_SIZE > boardShape.getColumns() - 1;
    }

    private boolean collideWithWallsAndBlocks(ShapeCoordinates blockPlaceCoordinates) {
        return blockPlaceCoordinates.intersectsWithBoard(boardShape);
    }

    public void removeBlock(ShapeCoordinates blockCoordinates) {
        if (isOutOfBounds(blockCoordinates)) {
            return;
        }

        applyChangesToTiles((rowIndex, colIndex) ->
                removeTileOfBlock(blockCoordinates, rowIndex, colIndex));
    }

    private void removeTileOfBlock(ShapeCoordinates blockToRemoveCoordinates, int rowIndex, int colIndex) {
        var x = blockToRemoveCoordinates.getX() + rowIndex;
        var y = blockToRemoveCoordinates.getY() + colIndex;
        var tile = blockToRemoveCoordinates.getBlock().getTiles()[rowIndex][colIndex];

        if (boardShape.getTiles()[x][y].getTileState() == tile.getTileState()) {
            boardShape.getTiles()[x][y].setTileState(TileState.EMPTY);
        }
    }

    public boolean isFolded() {
        return isFolded;
    }

    @FunctionalInterface
    private interface TileChanger {
        void changeTile(int rowIndex, int colIndex);
    }
}
