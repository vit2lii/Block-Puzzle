package com.example.Block.Puzzle;

import org.junit.jupiter.api.Test;
import sk.tuke.kpi.BlockPuzzle.core.board.*;
import sk.tuke.kpi.BlockPuzzle.core.exeptions.BlockNotFoundException;
import sk.tuke.kpi.BlockPuzzle.core.exeptions.InvalidPlacementException;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    private Tile createBoardTile() {
        return new Tile(TileState.EMPTY, Color.WHITE);
    }

    private Board createEmptyBoard() {
        return new Board(new Block(new Tile[][]
                {
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()}
                }

        ));
    }

    private Board createBoardClone(Board board) {
        final var boardSize = board.getBoardShape().getWidth();
        final var clonedTiles = new Tile[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                final var originalTile = board.getBoardShape().getTiles()[row][col];
                clonedTiles[row][col] = new Tile(originalTile.getTileState(), originalTile.getTileColor());
            }
        }

        return new Board(new Block(clonedTiles));
    }

    private Block createBlueSquareBlock() {
        return new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)}
                });
    }

    @Test
    void testPlaceBlockOnEmptyBoard() {
        final var board = createEmptyBoard();
        final var blockToPlace = createBlueSquareBlock();

        final var expectedBoard = new Board(new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()},
                        {createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile(), createBoardTile()}
                }

        ));

        board.placeBlock(blockToPlace, 0, 0);

        assertArrayEquals(expectedBoard.getBoardShape().getTiles(), board.getBoardShape().getTiles());
        assertTrue(board.getBlocksCoordinatesMap().containsKey(blockToPlace));
    }

    @Test
    void testPlaceBlockOnFilledWithOneTileBoard() {
        final var board = createEmptyBoard();
        board.getBoardShape().getTiles()[1][1].setTileState(TileState.MOVABLE);
        board.getBoardShape().getTiles()[1][1].setTileColor(Color.RED);

        final var expectedBoard = createBoardClone(board);
        final var blockToPlace = createBlueSquareBlock();

        assertThrows(InvalidPlacementException.class, () -> board.placeBlock(blockToPlace, 0, 0));
        assertArrayEquals(expectedBoard.getBoardShape().getTiles(), board.getBoardShape().getTiles());
        assertFalse(board.getBlocksCoordinatesMap().containsKey(blockToPlace));
    }

    @Test
    void testRemoveSquareBlock() {
        final var board = createEmptyBoard();
        final var blockToPlace = createBlueSquareBlock();

        board.placeBlock(blockToPlace, 0, 0);

        final var removedBlock = board.removeBlock(0, 0);

        assertArrayEquals(createEmptyBoard().getBoardShape().getTiles(), board.getBoardShape().getTiles());
        assertArrayEquals(blockToPlace.getTiles(), removedBlock.getTiles());
        assertFalse(board.getBlocksCoordinatesMap().containsKey(blockToPlace));
    }

    @Test
    void testRemoveSquareBlockOnWrongPosition() {
        final var board = createEmptyBoard();
        final var blockToPlace = createBlueSquareBlock();

        board.placeBlock(blockToPlace, 0, 0);
        final var boardWithSquare = createBoardClone(board);

        assertThrows(BlockNotFoundException.class, () -> board.removeBlock(0, 1));
        assertArrayEquals(boardWithSquare.getBoardShape().getTiles(), board.getBoardShape().getTiles());
        assertTrue(board.getBlocksCoordinatesMap().containsKey(blockToPlace));
    }

    @Test
    void testRemoveBlockNotFound() {
        final var board = createEmptyBoard();
        assertThrows(BlockNotFoundException.class, () -> board.removeBlock(0, 0));
    }

    @Test
    void testIsFolded() {
        final var board = new Board(createBlueSquareBlock());
        assertTrue(board.isBoardFolded());
    }

    @Test
    void testIsNotFolded() {
        final var board = createEmptyBoard();
        assertFalse(board.isBoardFolded());
    }
}
