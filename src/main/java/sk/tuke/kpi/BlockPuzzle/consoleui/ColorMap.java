package sk.tuke.kpi.BlockPuzzle.consoleui;

import sk.tuke.kpi.BlockPuzzle.core.board.Color;

import java.util.HashMap;
import java.util.Map;

public class ColorMap {
    private Map<Color, String> backgroundColorMap;
    private Map<Color, String> textColorMap;

    public ColorMap() {
        this.backgroundColorMap = initializeBackgorundColorMap();
        this.textColorMap = initializeTextColorMap();
    }

    private Map<Color, String> initializeBackgorundColorMap() {
        backgroundColorMap = new HashMap<>();
        backgroundColorMap.put(Color.BLACK, "\033[40;97m");
        backgroundColorMap.put(Color.RED, "\033[41;97m");
        backgroundColorMap.put(Color.GREEN, "\033[42;97m");
        backgroundColorMap.put(Color.YELLOW, "\033[43;97m");
        backgroundColorMap.put(Color.BLUE, "\033[44;97m");
        backgroundColorMap.put(Color.MAGENTA, "\033[45;97m");
        backgroundColorMap.put(Color.CYAN, "\033[46;97m");
        backgroundColorMap.put(Color.WHITE, "\033[47;97m");
        backgroundColorMap.put(Color.GRAY, "\033[100;97m");
        backgroundColorMap.put(Color.BRIGHT_RED, "\033[101;97m");
        backgroundColorMap.put(Color.BRIGHT_GREEN, "\033[102;97m");
        backgroundColorMap.put(Color.BRIGHT_YELLOW, "\033[103;97m");
        backgroundColorMap.put(Color.BRIGHT_BLUE, "\033[104;97m");
        backgroundColorMap.put(Color.BRIGHT_MAGENTA, "\033[105;97m");
        backgroundColorMap.put(Color.BRIGHT_CYAN, "\033[106;97m");
        return backgroundColorMap;
    }

    private Map<Color, String> initializeTextColorMap() {
        final Map<Color, String> textColorMap = new HashMap<>();
        textColorMap.put(Color.BLACK, "\033[30m");
        textColorMap.put(Color.RED, "\033[31m");
        textColorMap.put(Color.GREEN, "\033[32m");
        textColorMap.put(Color.YELLOW, "\033[33m");
        textColorMap.put(Color.BLUE, "\033[34m");
        textColorMap.put(Color.MAGENTA, "\033[35m");
        textColorMap.put(Color.CYAN, "\033[36m");
        textColorMap.put(Color.WHITE, "\033[37m");
        textColorMap.put(Color.GRAY, "\033[90m");
        textColorMap.put(Color.BRIGHT_RED, "\033[91m");
        textColorMap.put(Color.BRIGHT_GREEN, "\033[92m");
        textColorMap.put(Color.BRIGHT_YELLOW, "\033[93m");
        textColorMap.put(Color.BRIGHT_BLUE, "\033[94m");
        textColorMap.put(Color.BRIGHT_MAGENTA, "\033[95m");
        textColorMap.put(Color.BRIGHT_CYAN, "\033[96m");
        return textColorMap;
    }



    public String getBackgroundColor(Color color) {
        if (!backgroundColorMap.containsKey(color)) {
            return getDefaultColor();
        }
        return backgroundColorMap.get(color);
    }

    public String getTextColor(Color color) {
        if (!textColorMap.containsKey(color)) {
            return getDefaultColor();
        }

        return textColorMap.get(color);
    }

    public String getDefaultColor() {
        return "\033[0m";
    }
}
