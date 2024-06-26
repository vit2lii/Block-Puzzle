package sk.tuke.gamestudio.game.BlockPuzzle.game.levels;

import sk.tuke.gamestudio.game.BlockPuzzle.core.board.*;

import java.util.ArrayList;
import java.util.List;

public class HardLevel implements Level {
    @Override
    public Board generateBoard() {
        return Board.createEmptyBoard(7);
    }

    @Override
    public List<Block> generateBlocks() {

        final var block1 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile()}
                });
        final var block2 = new Block(new Tile[][]
                {
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.MAGENTA)},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.MAGENTA), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.MOVABLE, Color.MAGENTA)}
                });

        final var block3 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.RED), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.RED), Tile.createEmptyTile()},
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.RED)}
                });
        final var block4 = new Block(new Tile[][]
                {
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.BRIGHT_BLUE), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_BLUE), new Tile(TileState.MOVABLE, Color.BRIGHT_BLUE), new Tile(TileState.MOVABLE, Color.BRIGHT_BLUE)}
                });
        final var block5 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_GREEN), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_GREEN), new Tile(TileState.MOVABLE, Color.BRIGHT_GREEN), Tile.createEmptyTile()},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.BRIGHT_GREEN), new Tile(TileState.MOVABLE, Color.BRIGHT_GREEN)}
                });

        final var block6 = new Block(new Tile[][]
                {
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.BRIGHT_YELLOW), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_YELLOW), new Tile(TileState.MOVABLE, Color.BRIGHT_YELLOW), Tile.createEmptyTile()},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.BRIGHT_YELLOW), new Tile(TileState.MOVABLE, Color.BRIGHT_YELLOW)}
                });

        final var block7 = new Block(new Tile[][]
                {
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.BRIGHT_RED)},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.BRIGHT_RED), new Tile(TileState.MOVABLE, Color.BRIGHT_RED)},
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.BRIGHT_RED)}
                });

        final var block8 = new Block(new Tile[][]
                {
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.CYAN), new Tile(TileState.MOVABLE, Color.CYAN), new Tile(TileState.MOVABLE, Color.CYAN)},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.CYAN), new Tile(TileState.MOVABLE, Color.CYAN)}
                });

        final var block9 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_MAGENTA), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_MAGENTA), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.BRIGHT_MAGENTA), new Tile(TileState.MOVABLE, Color.BRIGHT_MAGENTA), Tile.createEmptyTile()}
                });
        final var block10 = new Block(new Tile[][]
                {
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.BRIGHT_CYAN)},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.BRIGHT_CYAN), new Tile(TileState.MOVABLE, Color.BRIGHT_CYAN)},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.BRIGHT_CYAN), new Tile(TileState.MOVABLE, Color.BRIGHT_CYAN)}
                });

        final var block11 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW)},
                        {new Tile(TileState.MOVABLE, Color.YELLOW), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.YELLOW), Tile.createEmptyTile(), Tile.createEmptyTile()}
                });




        final List<Block> blocks = new ArrayList<>();
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
