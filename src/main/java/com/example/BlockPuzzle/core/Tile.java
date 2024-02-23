package com.example.BlockPuzzle.core;

import lombok.Data;

@Data
public class Tile {
    private TileState tileState;
    private Color tileColor;

    public Tile(TileState tileState, Color tileColor) {
        this.tileState = tileState;
        this.tileColor = tileColor;
    }
}

