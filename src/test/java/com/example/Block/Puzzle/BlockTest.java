package com.example.Block.Puzzle;

import com.example.BlockPuzzle.core.board.Block;
import com.example.BlockPuzzle.core.board.Color;
import com.example.BlockPuzzle.core.board.Tile;
import com.example.BlockPuzzle.core.board.TileState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    @Test
    void testBlockInitialization() {
        var tiles = new Tile[Block.BLOCK_SIZE][Block.BLOCK_SIZE];
        for (int i = 0; i < Block.BLOCK_SIZE; i++) {
            for (int j = 0; j < Block.BLOCK_SIZE; j++) {
                tiles[i][j] = new Tile(TileState.EMPTY, Color.RED);
            }
        }

        var block = new Block(Block.BLOCK_SIZE, Block.BLOCK_SIZE, tiles);
        assertEquals(Block.BLOCK_SIZE, block.getRowsQnt());
        assertEquals(Block.BLOCK_SIZE, block.getColumnsQnt());
        assertArrayEquals(tiles, block.getTiles());
    }
}