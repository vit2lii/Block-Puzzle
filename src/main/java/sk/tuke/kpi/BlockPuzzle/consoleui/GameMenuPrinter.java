package sk.tuke.kpi.BlockPuzzle.consoleui;

import sk.tuke.kpi.BlockPuzzle.core.board.Color;

public class GameMenuPrinter {

    private final ColorMap colorMap;

    public GameMenuPrinter() {
        this.colorMap = new ColorMap();
    }

    public void printStartGameScreen() {
        System.out.println("\n" + colorMap.getTextColor(Color.YELLOW) +
                "██████╗░██╗░░░░░░█████╗░░█████╗░██╗░░██╗██████╗░██╗░░░██╗███████╗███████╗██╗░░░░░███████╗\n" +
                "██╔══██╗██║░░░░░██╔══██╗██╔══██╗██║░██╔╝██╔══██╗██║░░░██║╚════██║╚════██║██║░░░░░██╔════╝\n" +
                "██████╦╝██║░░░░░██║░░██║██║░░╚═╝█████═╝░██████╔╝██║░░░██║░░███╔═╝░░███╔═╝██║░░░░░█████╗░░\n" +
                "██╔══██╗██║░░░░░██║░░██║██║░░██╗██╔═██╗░██╔═══╝░██║░░░██║██╔══╝░░██╔══╝░░██║░░░░░██╔══╝░░\n" +
                "██████╦╝███████╗╚█████╔╝╚█████╔╝██║░╚██╗██║░░░░░╚██████╔╝███████╗███████╗███████╗███████╗\n" +
                "╚═════╝░╚══════╝░╚════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░░╚═════╝░╚══════╝╚══════╝╚══════╝╚══════╝\n" +
                "\t\t\t\t\t\t\t" + colorMap.getTextColor(Color.BLUE) + "Hello, welcome to the game!!!\n" + colorMap.getDefaultColor());
    }

    public void askPlayerNickname() {
        System.out.println("Please enter your nickname: ");
        System.out.print(colorMap.getTextColor(Color.BLUE) + "Nickname: " + colorMap.getDefaultColor());
    }

    public void askPlayerForNextMove() {
        System.out.println("What would you like to do?");
        System.out.println("1. Place a block");
        System.out.println("2. Remove a block");
        System.out.println("3. Surrender");
        System.out.println("Enter your choice (1, 2, or 3): ");
    }

    public void askOfGameLevel() {
        System.out.println("Please choose the game level:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("4. Daily Puzzle");
        System.out.println(colorMap.getTextColor(Color.RED) + "5. Exit" + colorMap.getDefaultColor());
        System.out.println("Enter your choice (1, 2, 3 4 or 5): ");
    }

    public void askWhatAndWherePlaceBlock() {
        System.out.println("Please choose the block you want to place and write X and Y coordinates where to place that block");
        System.out.println("Enter your choice in the form B X Y (B, X and Y are numbers. X and Y are coordinates of top left block`s corner): ");
    }

    public void askWhereRemoveBlock() {
        System.out.println("Please write X and Y coordinates to remove block from board");
        System.out.println("Enter your choice in the form X Y (X and Y are numbers): ");
    }

    public void reportPlayerAboutBadInput() {
        System.out.println(colorMap.getTextColor(Color.RED) + "Invalid input. Please try again." + colorMap.getDefaultColor());
    }

    public void askToProceed() {
        System.out.println("Shall we keep playing?");
        System.out.println("Type "+ colorMap.getTextColor(Color.GREEN)+ "'1'" +colorMap.getDefaultColor() +" to continue or "+ colorMap.getTextColor(Color.RED) + "'2'" + colorMap.getDefaultColor() +" to exit.\n");
    }

    public void askLeaveComment() {
        System.out.println("Would you like to leave a comment?");
        System.out.println("Type "+ colorMap.getTextColor(Color.GREEN)+ "'1'" +colorMap.getDefaultColor() +" to leave a comment or "+ colorMap.getTextColor(Color.RED) + "'2'" + colorMap.getDefaultColor() +" to skip.\n");
    }

    public void askLeaveRating() {
        System.out.println("Would you like to leave a rating?");
        System.out.println("Type "+ colorMap.getTextColor(Color.GREEN)+ "'1'" +colorMap.getDefaultColor() +" to leave a rating or "+ colorMap.getTextColor(Color.RED) + "'2'" + colorMap.getDefaultColor() +" to skip.\n");
    }

    public void printCommentAdding() {
        System.out.print("Please enter your comment (max 64 characters): ");
    }

    public void printRatingAdding() {
        System.out.print("Please enter your rating (1-5): ");
    }

    public void printCongratulations() {
        System.out.println("Congratulations!");
        System.out.println("You've completed the level successfully.\n");
    }
}
