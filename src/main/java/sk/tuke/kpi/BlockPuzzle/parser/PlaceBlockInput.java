package sk.tuke.kpi.BlockPuzzle.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sk.tuke.kpi.BlockPuzzle.core.utilities.Coordinate;

@Getter
@AllArgsConstructor
public class PlaceBlockInput {
    private final int blockIndex;
    private final Coordinate coordinate;
}
