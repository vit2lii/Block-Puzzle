package com.example.BlockPuzzle.core.game.levels;

import com.example.BlockPuzzle.core.board.Block;
import com.example.BlockPuzzle.core.board.Board;

import java.util.List;

public interface Level {
    Board generateBoard();
    List<Block> generateBlocks();
}
