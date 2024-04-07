package sk.tuke.gamestudio.BlockPuzzle.game.levels;

import sk.tuke.gamestudio.BlockPuzzle.core.board.Block;
import sk.tuke.gamestudio.BlockPuzzle.core.board.Board;

import java.util.List;

public interface Level {
    Board generateBoard();
    List<Block> generateBlocks();
}
