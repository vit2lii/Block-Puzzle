package com.example.Block.Puzzle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.BlockPuzzle.core.board.*;
import sk.tuke.gamestudio.game.BlockPuzzle.core.utilities.Coordinate;

public class PlacementValidatorTest {

    private Block boardShape;
    private Block blockToPlace;

    @BeforeEach
    public void setUp() {
        boardShape = new Block(new Tile[][]{
                {Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile()},
                {Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile()},
                {Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile()}
        });

        blockToPlace = new Block(new Tile[][]{
                {Tile.createEmptyTile(), Tile.createEmptyTile()},
                {new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED)}
        });
    }

    @Test
    public void testValidPlacement() {
        Coordinate validCoordinates = new Coordinate(0, 0);
        boolean isValid = PlacementValidator.isValidPlacement(boardShape, blockToPlace, validCoordinates);
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testInvalidPlacementOutOfBounds() {
        Coordinate invalidCoordinates = new Coordinate(2, 2);
        boolean isValid = PlacementValidator.isValidPlacement(boardShape, blockToPlace, invalidCoordinates);
        Assertions.assertFalse(isValid);
    }
}
