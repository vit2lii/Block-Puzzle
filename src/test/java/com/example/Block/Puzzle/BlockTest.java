package com.example.Block.Puzzle;

import sk.tuke.kpi.BlockPuzzle.core.board.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    @Test
    void testBlockInitialization() {
        var tiles = new Tile[Block.STANDARD_BLOCK_SIZE][Block.STANDARD_BLOCK_SIZE];
        for (int i = 0; i < Block.STANDARD_BLOCK_SIZE; i++) {
            for (int j = 0; j < Block.STANDARD_BLOCK_SIZE; j++) {
                tiles[i][j] = new Tile(TileState.EMPTY, Color.RED);
            }
        }

        final var block = new Block(tiles);
        assertArrayEquals(tiles, block.getTiles());
    }
}