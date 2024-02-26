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

    public boolean isEmptyTile() {
        return tileState == TileState.EMPTY;
    }
    public boolean isMovableTile() {
        return tileState == TileState.MOVABLE;
    }
}

