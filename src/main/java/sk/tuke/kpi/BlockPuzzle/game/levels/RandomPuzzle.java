package sk.tuke.kpi.BlockPuzzle.game.levels;

import sk.tuke.kpi.BlockPuzzle.core.board.Block;
import sk.tuke.kpi.BlockPuzzle.core.board.Board;

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
