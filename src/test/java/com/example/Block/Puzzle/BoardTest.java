package com.example.Block.Puzzle;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.BlockPuzzle.core.board.*;
import sk.tuke.gamestudio.game.BlockPuzzle.core.exeptions.BlockNotFoundException;
import sk.tuke.gamestudio.game.BlockPuzzle.core.exeptions.InvalidPlacementException;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Tile createBoardTile() {
        return new Tile(TileState.EMPTY, Color.WHITE);
    }

    @Test
    void testPlaceBlockOnEmptyBoard() {
        final var board = Board.createEmptyBoard(9);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();

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
        final var board = Board.createEmptyBoard(9);
        board.getBoardShape().getTiles()[1][1].setTileState(TileState.MOVABLE);
        board.getBoardShape().getTiles()[1][1].setTileColor(Color.RED);

        final var expectedBoard = ComponentInitialization.createBoardClone(board);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();

        assertThrows(InvalidPlacementException.class, () -> board.placeBlock(blockToPlace, 0, 0));
        assertArrayEquals(expectedBoard.getBoardShape().getTiles(), board.getBoardShape().getTiles());
        assertFalse(board.getBlocksCoordinatesMap().containsKey(blockToPlace));
    }

    @Test
    void testPlaceBlockOutOfBounds() {
        final var board = Board.createEmptyBoard(9);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();

        final var expectedBoard = ComponentInitialization.createBoardClone(board);

        assertThrows(InvalidPlacementException.class, () -> board.placeBlock(blockToPlace, 0, 9));
        assertArrayEquals(expectedBoard.getBoardShape().getTiles(), board.getBoardShape().getTiles());
        assertFalse(board.getBlocksCoordinatesMap().containsKey(blockToPlace));
    }

    @Test
    void testRemoveSquareBlock() {
        final var board = Board.createEmptyBoard(5);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();

        board.placeBlock(blockToPlace, 0, 0);

        final var removedBlock = board.removeBlock(0, 0);

        assertArrayEquals(Board.createEmptyBoard(5).getBoardShape().getTiles(), board.getBoardShape().getTiles());
        assertArrayEquals(blockToPlace.getTiles(), removedBlock.getTiles());
        assertFalse(board.getBlocksCoordinatesMap().containsKey(blockToPlace));
    }

    @Test
    void testRemoveBlockOutOfBounds() {
        final var board = Board.createEmptyBoard(5);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();

        board.placeBlock(blockToPlace, 0, 0);
        final var boardWithSquare = ComponentInitialization.createBoardClone(board);

        assertThrows(BlockNotFoundException.class, () -> board.removeBlock(0, 9));
        assertArrayEquals(boardWithSquare.getBoardShape().getTiles(), board.getBoardShape().getTiles());
        assertTrue(board.getBlocksCoordinatesMap().containsKey(blockToPlace));
    }

    @Test
    void testRemoveSquareBlockOnWrongPosition() {
        final var board = Board.createEmptyBoard(9);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();

        board.placeBlock(blockToPlace, 0, 0);

        final var boardWithSquare = ComponentInitialization.createBoardClone(board);

        assertThrows(BlockNotFoundException.class, () -> board.removeBlock(0, 1));
        assertArrayEquals(boardWithSquare.getBoardShape().getTiles(), board.getBoardShape().getTiles());
        assertTrue(board.getBlocksCoordinatesMap().containsKey(blockToPlace));
    }

    @Test
    void testRemoveBlockNotFound() {
        final var board = Board.createEmptyBoard(9);
        assertThrows(BlockNotFoundException.class, () -> board.removeBlock(0, 0));
    }

    @Test
    void testIsNotFolded() {
        final var board = Board.createEmptyBoard(9);
        assertFalse(board.isBoardFolded());
    }

    @Test
    void testPlaceBlockOnOccupiedBoard() {
        final var board = Board.createEmptyBoard(9);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();
        board.placeBlock(blockToPlace, 0, 0);

        final var blockToPlace2 = ComponentInitialization.createBlueSquareBlock();
        assertThrows(InvalidPlacementException.class, () -> board.placeBlock(blockToPlace2, 0, 0));
    }

    @Test
    void testRemoveBlockAndUpdateCoordinatesMap() {
        final var board = Board.createEmptyBoard(5);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();
        board.placeBlock(blockToPlace, 0, 0);

        final var removedBlock = board.removeBlock(0, 0);
        assertFalse(board.getBlocksCoordinatesMap().containsKey(removedBlock));
    }

    @Test
    void testRemovedBlock() {
        final var board = Board.createEmptyBoard(5);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();
        board.placeBlock(blockToPlace, 0, 0);

        final var removedBlock = board.removeBlock(0, 0);
        assertEquals(blockToPlace, removedBlock);
    }

    @Test
    void testIsFoldedWithPartiallyFilledBoard() {
        final var board = Board.createEmptyBoard(9);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();
        board.placeBlock(blockToPlace, 0, 0);
        assertFalse(board.isBoardFolded());
    }

    @Test
    void testIsFoldedWithFullyFilledBoard() {
        final var board = Board.createEmptyBoard(3);
        final var blockToPlace = ComponentInitialization.createBlueSquareBlock();
        board.placeBlock(blockToPlace, 0, 0);
        assertTrue(board.isBoardFolded());
    }

    @Test
    void testPlaceBlockOnBoardWithOverlap() {
        final var board = Board.createEmptyBoard(5);
        final var blockToPlace1 = ComponentInitialization.createBlueSquareBlock();
        final var blockToPlace2 = ComponentInitialization.createBlueSquareBlock();
        board.placeBlock(blockToPlace1, 0, 0);
        assertThrows(InvalidPlacementException.class, () -> board.placeBlock(blockToPlace2, 1, 1));
    }

}
