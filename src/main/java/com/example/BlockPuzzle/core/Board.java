package com.example.BlockPuzzle.core;

import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
public class Board {
    private Block boardShape;
    private Map<Block, Coordinate> blocksCoordinatesMap;

    public Board(Block boardShape) {
        this.boardShape = boardShape;
        this.blocksCoordinatesMap = new HashMap<>();
    }

    public void placeBlockOnTiles(Block blockToPlace, int rowIndex, int colIndex) {
        var blockToPlaceCoordinate = new Coordinate(rowIndex, colIndex);

        if (!PlacementValidator.isValidPlacement(boardShape, blockToPlace, blockToPlaceCoordinate)) {
            //TODO throw exception
            return;
        }

        placeBlockOnTiles(blockToPlace, blockToPlaceCoordinate);
        blocksCoordinatesMap.put(blockToPlace, blockToPlaceCoordinate);
    }

    public void removeBlock(int rowIndex, int colIndex) {
        var blockToRemoveCoordinate = new Coordinate(rowIndex, colIndex);

        if (!blocksCoordinatesMap.containsValue(blockToRemoveCoordinate)) {
            return;
        }

        removeBlockOnTiles(blockToRemoveCoordinate);
    }

    public boolean isFolded() {
        return Arrays.stream(boardShape.getTiles()).flatMap(Arrays::stream).noneMatch(tile -> tile.getTileState() == TileState.EMPTY);
    }

    private void placeBlockOnTiles(Block blockToPlace, Coordinate blockToPlaceCoordinates) {
        applyChangesToTiles((rowIndex, colIndex) -> {
            var x = blockToPlaceCoordinates.getX() + rowIndex;
            var y = blockToPlaceCoordinates.getY() + colIndex;

            if (isEmptyTile(x, y)) {
                placeTile(blockToPlace.getTiles()[rowIndex][colIndex], x, y);
            }
        });
    }

    public void removeBlockOnTiles(Coordinate blockToRemoveCoordinates) {
        applyChangesToTiles((rowIndex, colIndex) -> {
            var tileToRemove = boardShape.getTiles()[blockToRemoveCoordinates.getX() + rowIndex][blockToRemoveCoordinates.getY() + colIndex];

            if (tileToRemove.isMovableTile()) {
                removeTile(blockToRemoveCoordinates.getX() + rowIndex, blockToRemoveCoordinates.getY() + colIndex);
            }
        });
    }

    public void applyChangesToTiles(TileChanger tileChanger) {
        for (int rowIndex = 0; rowIndex < Block.BLOCK_SIZE; rowIndex++) {
            for (int colIndex = 0; colIndex < Block.BLOCK_SIZE; colIndex++) {
                tileChanger.changeTile(rowIndex, colIndex);
            }
        }
    }

    public boolean isEmptyTile(int x, int y) {
        return boardShape.getTiles()[x][y].isEmptyTile();
    }

    public void placeTile(Tile tileToPlace, int rowIndex, int colIndex) {
        boardShape.getTiles()[rowIndex][colIndex] = tileToPlace;
    }

    public void removeTile(int x, int y) {
        boardShape.getTiles()[x][y].setTileState(TileState.EMPTY);
    }

    @FunctionalInterface
    public interface TileChanger {
        void changeTile(int rowIndex, int colIndex);
    }
}