package sk.tuke.gamestudio.game.BlockPuzzle.core.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Block {
    public static final int STANDARD_BLOCK_SIZE = 3;
    private Tile[][] tiles;
    private int width;
    private int height;

    public Block(Tile[][] tiles) {
        this.tiles = tiles;
        height = tiles.length;
        width = tiles[0].length;
    }

    public Block createBlockInStandardSize() {
        final var newBlock = new Block(new Tile[][]
                {
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)},
                        {new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE), new Tile(TileState.EMPTY, Color.WHITE)}
                });

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                newBlock.getTiles()[i][j].setTile(tiles[i][j]);
            }
        }

        return newBlock;
    }
}
