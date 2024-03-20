package com.example.Block.Puzzle;

import sk.tuke.kpi.BlockPuzzle.core.board.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    @Test
    void testEmptyTile() {
        Tile emptyTile = new Tile(TileState.EMPTY, Color.RED);
        assertTrue(emptyTile.isEmptyTile());
        assertFalse(emptyTile.isMovableTile());
    }

    @Test
    void testMovableTile() {
        Tile movableTile = new Tile(TileState.MOVABLE, Color.BLUE);
        assertTrue(movableTile.isMovableTile());
        assertFalse(movableTile.isEmptyTile());
    }

    @Test
    void testNonEmptyNonMovableTile() {
        Tile nonEmptyTile = new Tile(TileState.BLOCKED, Color.GREEN);
        assertFalse(nonEmptyTile.isEmptyTile());
        assertFalse(nonEmptyTile.isMovableTile());
    }
}