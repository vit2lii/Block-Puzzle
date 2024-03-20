package sk.tuke.kpi.BlockPuzzle.game.levels;

import sk.tuke.kpi.BlockPuzzle.core.board.Block;
import sk.tuke.kpi.BlockPuzzle.core.board.Board;

import java.util.List;

public interface Level {
    Board generateBoard();
    List<Block> generateBlocks();
}
