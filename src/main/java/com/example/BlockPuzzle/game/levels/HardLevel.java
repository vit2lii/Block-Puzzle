package com.example.BlockPuzzle.game.levels;

import com.example.BlockPuzzle.core.board.*;

import java.util.ArrayList;
import java.util.List;

public class HardLevel implements Level {
    @Override
    public Board generateBoard() {
        return new Board(new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)}
                }

        ));
    }

    @Override
    public List<Block> generateBlocks() {

        var block1 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)}
                });
        var block2 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.MAGENTA)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.MOVABLE, Color.MAGENTA)}
                });

        var block3 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.RED)}
                });
        var block4 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BRIGHT_BLUE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_BLUE), new Tile(TileState.MOVABLE, Color.BRIGHT_BLUE), new Tile(TileState.MOVABLE, Color.BRIGHT_BLUE)}
                });
        var block5 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_GREEN), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_GREEN), new Tile(TileState.MOVABLE, Color.BRIGHT_GREEN), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BRIGHT_GREEN), new Tile(TileState.MOVABLE, Color.BRIGHT_GREEN)}
                });

        var block6 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BRIGHT_YELLOW), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_YELLOW), new Tile(TileState.MOVABLE, Color.BRIGHT_YELLOW), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BRIGHT_YELLOW), new Tile(TileState.MOVABLE, Color.BRIGHT_YELLOW)}
                });

        var block7 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BRIGHT_RED)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BRIGHT_RED), new Tile(TileState.MOVABLE, Color.BRIGHT_RED)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BRIGHT_RED)}
                });

        var block8 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.CYAN), new Tile(TileState.MOVABLE, Color.CYAN), new Tile(TileState.MOVABLE, Color.CYAN)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.CYAN), new Tile(TileState.MOVABLE, Color.CYAN)}
                });

        var block9 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_MAGENTA), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_MAGENTA), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_MAGENTA), new Tile(TileState.MOVABLE, Color.BRIGHT_MAGENTA), new Tile(TileState.EMPTY, Color.WHITE)}
                });
        var block10 = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BRIGHT_CYAN)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BRIGHT_CYAN), new Tile(TileState.MOVABLE, Color.BRIGHT_CYAN)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.MOVABLE, Color.BRIGHT_CYAN), new Tile(TileState.MOVABLE, Color.BRIGHT_CYAN)}
                });

        var block11 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW)},
                        {new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)}
                });




        List<Block> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);
        blocks.add(block6);
        blocks.add(block7);
        blocks.add(block8);
        blocks.add(block9);
        blocks.add(block10);
        blocks.add(block11);

        return blocks;
    }
}
