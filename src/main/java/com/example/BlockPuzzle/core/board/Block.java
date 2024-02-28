package com.example.BlockPuzzle.core.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Block {
    public static final int BLOCK_SIZE = 3;
    private int rowsQnt;
    private int columnsQnt;
    private Tile[][] tiles;

    public Block(int rows, int columns, Tile[][] tiles) {
        this.rowsQnt = rows;
        this.columnsQnt = columns;
        this.tiles = tiles;
    }
}
