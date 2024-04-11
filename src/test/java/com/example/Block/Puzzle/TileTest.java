package com.example.Block.Puzzle;

import sk.tuke.gamestudio.game.BlockPuzzle.core.board.Color;
import sk.tuke.gamestudio.game.BlockPuzzle.core.board.Tile;
import sk.tuke.gamestudio.game.BlockPuzzle.core.board.TileState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    @Test
    void testEmptyTile() {
        final var emptyTile = new Tile(TileState.EMPTY, Color.RED);
        assertTrue(emptyTile.isEmptyTile());
        assertFalse(emptyTile.isMovableTile());
    }

    @Test
    void testMovableTile() {
        final var movableTile = new Tile(TileState.MOVABLE, Color.BLUE);
        assertTrue(movableTile.isMovableTile());
        assertFalse(movableTile.isEmptyTile());
    }

    @Test
    void testNonEmptyNonMovableTile() {
        final var nonEmptyTile = new Tile(TileState.BLOCKED, Color.GREEN);
        assertFalse(nonEmptyTile.isEmptyTile());
        assertFalse(nonEmptyTile.isMovableTile());
    }
}