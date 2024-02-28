package com.example.Block.Puzzle;

import com.example.BlockPuzzle.core.board.Color;
import com.example.BlockPuzzle.core.board.Tile;
import com.example.BlockPuzzle.core.board.TileState;
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