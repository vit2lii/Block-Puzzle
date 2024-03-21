package sk.tuke.kpi.BlockPuzzle.game.levels;

import sk.tuke.kpi.BlockPuzzle.core.board.*;
import sk.tuke.kpi.BlockPuzzle.core.utilities.Coordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLevelGenerator {
    private static final int BOARD_SIZE = 5;
    private static final int MAX_BLOCK_NUMBER = 12;
    private static final List<Coordinate> DIRECTIONS = initializeDirections();
    private final List<Color> availableColors;

    public RandomLevelGenerator() {
        this.availableColors = new ArrayList<>(List.of(Color.values()));
        availableColors.remove(Color.WHITE);
        availableColors.remove(Color.BLACK);
    }

    public Board createBoard() {
        return Board.createEmptyBoard(BOARD_SIZE);
    }

    private List<List<Coordinate>> createFoldedBoard() {
        var tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
        var blockCoordinates = new ArrayList<Coordinate>();
        var blockCoordinatesList = new ArrayList<List<Coordinate>>();
        initializeBoardAndBlockCoordinate(tiles, blockCoordinates);

        var blockNumber = 0;
        while (!blockCoordinates.isEmpty() && blockNumber < MAX_BLOCK_NUMBER) {
            var randomCoordinate = getRandomCoordinate(blockCoordinates);
            var blockCoordinate = generateRandomConnectedBlock(tiles, randomCoordinate);
            if (blockCoordinate != null) {
                blockNumber++;
                blockCoordinatesList.add(blockCoordinate);
            }
        }

        return blockCoordinatesList;
    }


    public List<Block> createBlocks() {
        return createBlocks(createFoldedBoard());
    }

    public List<Block> createBlocks(List<List<Coordinate>> blockCoordinatesList) {
        var blocks = new ArrayList<Block>();

        for (var blockCoordinates : blockCoordinatesList) {
            var block = generateBlockFromCoordinates(blockCoordinates);
            if (block != null) {
                blocks.add(block);
            }
        }

        return blocks;
    }

    private Block generateBlockFromCoordinates(List<Coordinate> blockCoordinates) {
        if (blockCoordinates.isEmpty()) {
            return null;
        }

        var minX = Integer.MAX_VALUE;
        var minY = Integer.MAX_VALUE;
        var maxX = Integer.MIN_VALUE;
        var maxY = Integer.MIN_VALUE;

        for (var coordinate : blockCoordinates) {
            var x = coordinate.getX();
            var y = coordinate.getY();
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }

        var width = maxX - minX + 1;
        var height = maxY - minY + 1;

        var tiles = new Tile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = Tile.createEmptyTile();
            }
        }

        var color = getRandomColor();
        for (var coordinate : blockCoordinates) {
            int x = coordinate.getX() - minX;
            int y = coordinate.getY() - minY;
            tiles[y][x].setTile(new Tile(TileState.MOVABLE, color));
        }

        return new Block(tiles);
    }

    private Color getRandomColor() {
        var randomIndex = (int) (Math.random() * availableColors.size());
        var color = availableColors.get(randomIndex);
        availableColors.remove(color);
        return color;
    }

    private Coordinate getRandomCoordinate(List<Coordinate> blockCoordinates) {
        Collections.shuffle(blockCoordinates);
        return blockCoordinates.remove(0);
    }

    private List<Coordinate> generateRandomConnectedBlock(Tile[][] tiles, Coordinate coordinate) {
        var blockCoordinates = new ArrayList<Coordinate>();
        try {
            var availableDirections = new ArrayList<Coordinate>();

            for (var direction : DIRECTIONS) {
                var x = coordinate.getX() + direction.getX();
                var y = coordinate.getY() + direction.getY();

                if (isValidPosition(x, y) && tiles[x][y].isEmptyTile()) {
                    availableDirections.add(new Coordinate(direction.getX(), direction.getY()));
                }
            }

            if (availableDirections.isEmpty() || !tiles[coordinate.getX()][coordinate.getY()].isEmptyTile()) {
                return null;
            }

            tiles[coordinate.getX()][coordinate.getY()].setTile(new Tile(TileState.MOVABLE, Color.BLACK));
            blockCoordinates.add(coordinate);

            for (var direction : availableDirections) {
                int x = coordinate.getX() + direction.getX();
                int y = coordinate.getY() + direction.getY();
                tiles[x][y].setTile(new Tile(TileState.MOVABLE, Color.BLACK));
                blockCoordinates.add(new Coordinate(x, y));
            }


        } catch (ArrayIndexOutOfBoundsException ignored) {
            return null;
        }
        return blockCoordinates;
    }

    private void initializeBoardAndBlockCoordinate(Tile[][] tiles, List<Coordinate> blockAvailableCoordinates) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                tiles[i][j] = Tile.createEmptyTile();
                blockAvailableCoordinates.add(new Coordinate(i, j));
            }
        }
    }

    private static List<Coordinate> initializeDirections() {
        var directions = new ArrayList<Coordinate>();
        directions.add(new Coordinate(-1, 0));
        directions.add(new Coordinate(1, 0));
        directions.add(new Coordinate(0, -1));
        directions.add(new Coordinate(0, 1));
        directions.add(new Coordinate(-1, -1));
        directions.add(new Coordinate(-1, 1));
        directions.add(new Coordinate(1, -1));
        directions.add(new Coordinate(1, 1));
        return directions;
    }

    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }
}