package com.example.BlockPuzzle.game.levels;

import com.example.BlockPuzzle.core.board.*;

import java.util.ArrayList;
import java.util.List;

public class EasyLevel implements Level {
    @Override
    public Board generateBoard() {
        return new Board(new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)}
                }

        ));
    }

    @Override
    public List<Block> generateBlocks() {
        var block1 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED)},
                        {new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.EMPTY, Color.WHITE)}
                });

        var block2 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW)},
                        {new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW)}
                });

        var block3 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.GREEN)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.GREEN)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.GREEN), new Tile(TileState.MOVABLE, Color.GREEN)}
                });

        var block4 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.EMPTY, Color.WHITE)}
                });

        var block5 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.EMPTY, Color.WHITE)}
                });



        List<Block> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);

        return blocks;
    }
}
