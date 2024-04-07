package sk.tuke.gamestudio.BlockPuzzle.service;

import sk.tuke.gamestudio.BlockPuzzle.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score) throws ScoreException;
    List<Score> getTopScores(String game) throws ScoreException;
    void reset() throws ScoreException;
}
