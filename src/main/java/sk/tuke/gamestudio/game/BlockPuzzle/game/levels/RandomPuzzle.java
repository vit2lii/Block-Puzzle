package sk.tuke.gamestudio.game.BlockPuzzle.game.levels;

import sk.tuke.gamestudio.game.BlockPuzzle.core.board.Block;
import sk.tuke.gamestudio.game.BlockPuzzle.core.board.Board;

import java.util.List;

public class RandomPuzzle implements Level {
    @Override
    public Board generateBoard() {
        return new RandomLevelGenerator().createBoard();
    }

    @Override
    public List<Block> generateBlocks() {
        return new RandomLevelGenerator().createBlocks();
    }
}
