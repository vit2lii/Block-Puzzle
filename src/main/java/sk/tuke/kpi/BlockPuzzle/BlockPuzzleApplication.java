package sk.tuke.kpi.BlockPuzzle;

import sk.tuke.kpi.BlockPuzzle.game.ConsoleBlockPuzzleGame;

public class BlockPuzzleApplication {
    public static void main(String[] args) {
        var consoleBlockPuzzleGame = new ConsoleBlockPuzzleGame();
        consoleBlockPuzzleGame.startGame();
    }
}
