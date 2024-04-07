package com.example.Block.Puzzle;

import sk.tuke.gamestudio.BlockPuzzle.core.board.*;

public class ComponentInitialization {
    static Board createBoardClone(Board board) {
        final var boardSize = board.getBoardShape().getWidth();
        final var clonedTiles = new Tile[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                final var originalTile = board.getBoardShape().getTiles()[row][col];
                clonedTiles[row][col] = new Tile(originalTile.getTileState(), originalTile.getTileColor());
            }
        }

        return new Board(new Block(clonedTiles));
    }

    static Block createBlueSquareBlock() {
        return new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)}
                });
    }
}
