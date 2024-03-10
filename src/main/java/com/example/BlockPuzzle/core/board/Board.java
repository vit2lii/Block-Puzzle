package com.example.BlockPuzzle.core.board;

import com.example.BlockPuzzle.core.utilities.Coordinate;
import com.example.BlockPuzzle.core.exeptions.BlockNotFoundException;
import com.example.BlockPuzzle.core.exeptions.InvalidPlacementException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Getter
@Setter
@EqualsAndHashCode
public class Board {
    private Block boardShape;
    private Map<Block, Coordinate> blocksCoordinatesMap;

    public Board(Block boardShape) {
        this.boardShape = boardShape;
        this.blocksCoordinatesMap = new HashMap<>();
    }

    public void placeBlock(Block blockToPlace, int rowPlaceIndex, int colPlaceIndex) {
        var blockToPlaceCoordinate = new Coordinate(rowPlaceIndex, colPlaceIndex);

        if (!PlacementValidator.isValidPlacement(boardShape, blockToPlace, blockToPlaceCoordinate)) {
            throw new InvalidPlacementException("Invalid placement of block.");
        }

        placeBlockOnTiles(blockToPlace, blockToPlaceCoordinate);
        blocksCoordinatesMap.put(blockToPlace, blockToPlaceCoordinate);
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

    private void placeTile(Tile tileToPlace, int rowIndex, int colIndex) {
        boardShape.getTiles()[rowIndex][colIndex].setTileState(tileToPlace.getTileState());
        boardShape.getTiles()[rowIndex][colIndex].setTileColor(tileToPlace.getTileColor());
    }

    public Block removeBlock(int rowIndex, int colIndex) {
        var blockToRemoveCoordinate = new Coordinate(rowIndex, colIndex);

        var blockToRemove = blocksCoordinatesMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(blockToRemoveCoordinate))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new BlockNotFoundException("Block not found"));

        removeBlockOnTiles(blockToRemoveCoordinate);
        blocksCoordinatesMap.remove(blockToRemove);

        return blockToRemove;
    }

    private void removeBlockOnTiles(Coordinate blockToRemoveCoordinates) {
        applyChangesToTiles((rowIndex, colIndex) -> {
            var tileToRemove = boardShape.getTiles()[blockToRemoveCoordinates.getX() + rowIndex][blockToRemoveCoordinates.getY() + colIndex];

            if (tileToRemove.isMovableTile()) {
                removeTile(blockToRemoveCoordinates.getX() + rowIndex, blockToRemoveCoordinates.getY() + colIndex);
            }
        });
    }

    private void removeTile(int rowIndex, int colIndex) {
        boardShape.getTiles()[rowIndex][colIndex] = Tile.createBoardTile();
    }

    private boolean isEmptyTile(int x, int y) {
        return boardShape.getTiles()[x][y].isEmptyTile();
    }

    private void applyChangesToTiles(TileChanger tileChanger) {
        IntStream.range(0, Block.BLOCK_SIZE)
                .forEach(rowIndex -> IntStream.range(0, Block.BLOCK_SIZE)
                        .forEach(colIndex -> tileChanger.changeTile(rowIndex, colIndex)));
    }

    public boolean isBoardFolded() {
        return Arrays.stream(boardShape.getTiles()).flatMap(Arrays::stream).noneMatch(tile -> tile.getTileState() == TileState.EMPTY);
    }

    @FunctionalInterface
    private interface TileChanger {
        void changeTile(int rowIndex, int colIndex);
    }
}