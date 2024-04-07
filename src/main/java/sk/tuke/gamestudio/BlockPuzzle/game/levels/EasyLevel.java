package sk.tuke.gamestudio.BlockPuzzle.game.levels;

import sk.tuke.gamestudio.BlockPuzzle.core.board.*;

import java.util.ArrayList;
import java.util.List;

public class EasyLevel implements Level {
    @Override
    public Board generateBoard() {
        return Board.createEmptyBoard(5);
    }

    @Override
    public List<Block> generateBlocks() {
        final var block1 = new Block(new Tile[][]
                {
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED)},
                        {new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED), new Tile(TileState.MOVABLE, Color.RED)},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.RED), Tile.createEmptyTile()}
                });


        final var block2 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW)},
                        {new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW), new Tile(TileState.MOVABLE, Color.YELLOW)}
                });

        final var block3 = new Block(new Tile[][]
                {
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.GREEN)},
                        {Tile.createEmptyTile(), new Tile(TileState.MOVABLE, Color.GREEN)},
                        {new Tile(TileState.MOVABLE, Color.GREEN), new Tile(TileState.MOVABLE, Color.GREEN)}
                });

        final var block4 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)},
                        {new Tile(TileState.MOVABLE, Color.BLUE), Tile.createEmptyTile()},
                        {new Tile(TileState.MOVABLE, Color.BLUE), new Tile(TileState.MOVABLE, Color.BLUE)}
                });

        final var block5 = new Block(new Tile[][]
                {
                        {new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.MOVABLE, Color.MAGENTA)},
                        {new Tile(TileState.MOVABLE, Color.MAGENTA), new Tile(TileState.MOVABLE, Color.MAGENTA)}
                });

        final List<Block> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);

        return blocks;
    }
}
