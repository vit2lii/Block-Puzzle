package sk.tuke.kpi.BlockPuzzle.game.levels;

import sk.tuke.kpi.BlockPuzzle.core.board.*;

import java.util.ArrayList;
import java.util.List;

public class MediumLevel implements Level {
    @Override
    public Board generateBoard() {
        return new Board(new Block(new Tile[][]
                {
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile(), new Tile(TileState.BLOCKED, Color.BLACK)},
                        {new Tile(TileState.BLOCKED, Color.BLACK), Tile.createEmptyTile(), new Tile(TileState.BLOCKED, Color.BLACK), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {Tile.createEmptyTile(), new Tile(TileState.BLOCKED, Color.BLACK), Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile(), new Tile(TileState.BLOCKED, Color.BLACK), Tile.createEmptyTile()},
                        {Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile(), Tile.createEmptyTile()}

                }));
    }

    @Override
    public List<Block> generateBlocks() {
        final var block1 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW)},
                });
        final var block2 = new Block(new Tile[][]
                {
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.CYAN), new Tile(TileState.MOVABLE, Color.CYAN)},
                        {new Tile(TileState.MOVABLE, Color.CYAN), Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.CYAN)},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.CYAN), new Tile(TileState.MOVABLE, Color.CYAN)}
                });
        final var block3 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.GREEN)},
                        {new Tile(TileState.MOVABLE, Color.GREEN)},
                        {new Tile(TileState.MOVABLE, Color.GREEN)}
                });

        final var block4 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.RED), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED)},
                        {new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED)}
                });

        final var block5 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.MAGENTA), Tile.createEmptyTile(), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.MOVABLE, Color.MAGENTA)}
                });

        List<Block> blocks = new ArrayList<>();
        blocks.add(block2);
        blocks.add(block4);
        blocks.add(block5);
        blocks.add(block3);
        blocks.add(block1);

        return blocks;
    }
}
