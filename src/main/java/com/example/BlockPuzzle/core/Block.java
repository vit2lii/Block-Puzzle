package com.example.BlockPuzzle.core;

import lombok.Data;

@Data
public class Block {
    public static final int BLOCK_SIZE = 3;
    private int rows;
    private int columns;
    private Tile[][] tiles;
    private Color color;

    public Block(int rows, int columns, Tile[][] tiles, Color color) {
        this.rows = rows;
        this.columns = columns;
        this.tiles = tiles;
        this.color = color;
    }
}
