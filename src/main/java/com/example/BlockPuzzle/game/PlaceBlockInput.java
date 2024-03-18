package com.example.BlockPuzzle.game;

import com.example.BlockPuzzle.core.utilities.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaceBlockInput {
    private final int blockIndex;
    private final Coordinate coordinate;
}
