package com.example.BlockPuzzle.core.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Block {
    public static final int BLOCK_SIZE = 3;
    private Tile[][] tiles;

    public Block(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getBlockWidth() {
        return tiles.length;
    }
}
