package sk.tuke.kpi.BlockPuzzle.gamestudio.service;

public class ScoreException extends RuntimeException {
    public ScoreException(String message) {
        super(message);
    }

    public ScoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
