package sk.tuke.gamestudio.game.BlockPuzzle.game.levels;

import sk.tuke.gamestudio.game.BlockPuzzle.core.board.Block;
import sk.tuke.gamestudio.game.BlockPuzzle.core.board.Board;

import java.util.List;

public interface Level {
    Board generateBoard();
    List<Block> generateBlocks();
}
