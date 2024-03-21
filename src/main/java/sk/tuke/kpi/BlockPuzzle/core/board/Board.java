package sk.tuke.kpi.BlockPuzzle.core.board;

import sk.tuke.kpi.BlockPuzzle.core.exeptions.*;
import sk.tuke.kpi.BlockPuzzle.core.utilities.Coordinate;

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
        final var blockToPlaceCoordinate = new Coordinate(rowPlaceIndex, colPlaceIndex);

        if (!PlacementValidator.isValidPlacement(boardShape, blockToPlace, blockToPlaceCoordinate)) {
            throw new InvalidPlacementException("Invalid placement of block.");
        }

        placeBlockOnTiles(blockToPlace, blockToPlaceCoordinate);
        blocksCoordinatesMap.put(blockToPlace, blockToPlaceCoordinate);
    }

    private void placeBlockOnTiles(Block blockToPlace, Coordinate blockToPlaceCoordinates) {
        final var blockWidth = blockToPlace.getWidth();
        final var blockHeight = blockToPlace.getHeight();

        applyChangesToTiles(blockWidth, blockHeight, (rowIndex, colIndex) -> {
            final var x = blockToPlaceCoordinates.getX() + rowIndex;
            final var y = blockToPlaceCoordinates.getY() + colIndex;

            if (isEmptyTile(x, y)) {
                placeTile(blockToPlace.getTiles()[rowIndex][colIndex], x, y);
            }
        });
    }

    private void placeTile(Tile tileToPlace, int rowIndex, int colIndex) {
        boardShape.getTiles()[rowIndex][colIndex].setTile(tileToPlace);
    }

    public Block removeBlock(int rowIndex, int colIndex) {
        final var blockToRemoveCoordinate = new Coordinate(rowIndex, colIndex);

        final var blockToRemove = blocksCoordinatesMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(blockToRemoveCoordinate))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new BlockNotFoundException("Block not found"));

        removeBlockOnTiles(blockToRemoveCoordinate, blockToRemove.getWidth(), blockToRemove.getHeight());
        blocksCoordinatesMap.remove(blockToRemove);

        return blockToRemove;
    }

    private void removeBlockOnTiles(Coordinate blockToRemoveCoordinates, int blockWidth, int blockHeight) {
        applyChangesToTiles(blockWidth, blockHeight, (rowIndex, colIndex) -> {
            final var x = blockToRemoveCoordinates.getX() + rowIndex;
            final var y = blockToRemoveCoordinates.getY() + colIndex;

            if (x >= 0 && y >= 0 && x < boardShape.getHeight() && y < boardShape.getWidth()) {
                removeTile(x, y);
            }
        });
    }

    private void removeTile(int rowIndex, int colIndex) {
        boardShape.getTiles()[rowIndex][colIndex] = Tile.createBoardTile();
    }

    private boolean isEmptyTile(int x, int y) {
        return x >= 0 && y >= 0 && x < boardShape.getHeight() && y < boardShape.getWidth() &&
                boardShape.getTiles()[x][y].isEmptyTile();
    }

    private void applyChangesToTiles(int blockWidth, int blockHeight, TileChanger tileChanger) {
        IntStream.range(0, blockHeight).forEach(rowIndex ->
                IntStream.range(0, blockWidth).forEach(colIndex -> tileChanger.changeTile(rowIndex, colIndex)));
    }

    public boolean isBoardFolded() {
        return Arrays.stream(boardShape.getTiles()).flatMap(Arrays::stream).noneMatch(tile -> tile.getTileState() == TileState.EMPTY);
    }

    public static Board createEmptyBoard(int boardSize) {
        var tiles = new Tile[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                tiles[i][j] = Tile.createBoardTile();
            }
        }
        return new Board(new Block(tiles));
    }

    @FunctionalInterface
    private interface TileChanger {
        void changeTile(int rowIndex, int colIndex);
    }
}
