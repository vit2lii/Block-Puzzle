package sk.tuke.gamestudio.BlockPuzzle.core.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
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

    public static Tile createEmptyTile() {
        return new Tile(TileState.EMPTY, Color.WHITE);
    }

    public void setTile(Tile tile) {
        tileState = tile.tileState;
        tileColor = tile.tileColor;
    }
}

