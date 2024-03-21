package sk.tuke.kpi.BlockPuzzle.consoleui;

import sk.tuke.kpi.BlockPuzzle.core.board.Color;
import sk.tuke.kpi.BlockPuzzle.gamestudio.entity.Comment;
import sk.tuke.kpi.BlockPuzzle.gamestudio.entity.Score;

import java.util.List;

public class GameMenuPrinter {

    private final ColorMap colorMap;

    public GameMenuPrinter() {
        this.colorMap = new ColorMap();
    }

    public void printStartGameScreen() {
        System.out.println("\n" + colorMap.getTextColor(Color.BLUE) +
                "██████╗░██╗░░░░░░█████╗░░█████╗░██╗░░██╗██████╗░██╗░░░██╗███████╗███████╗██╗░░░░░███████╗\n" +
                "██╔══██╗██║░░░░░██╔══██╗██╔══██╗██║░██╔╝██╔══██╗██║░░░██║╚════██║╚════██║██║░░░░░██╔════╝\n" +
                "██████╦╝██║░░░░░██║░░██║██║░░╚═╝█████═╝░██████╔╝██║░░░██║░░███╔═╝░░███╔═╝██║░░░░░█████╗░░\n" +
                "██╔══██╗██║░░░░░██║░░██║██║░░██╗██╔═██╗░██╔═══╝░██║░░░██║██╔══╝░░██╔══╝░░██║░░░░░██╔══╝░░\n" +
                "██████╦╝███████╗╚█████╔╝╚█████╔╝██║░╚██╗██║░░░░░╚██████╔╝███████╗███████╗███████╗███████╗\n" +
                "╚═════╝░╚══════╝░╚════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░░╚═════╝░╚══════╝╚══════╝╚══════╝╚══════╝\n" +
                "\n\t\t\t\t\t\t\t" + colorMap.getTextColor(Color.MAGENTA) + "HELLO, WELCOME TO THE GAME!!!\n" + colorMap.getDefaultColor());
    }

    public void printHallOfFame(List<Score> topScores) {
        System.out.println(colorMap.getTextColor(Color.MAGENTA) + "Hall of Fame:" + colorMap.getDefaultColor());
        for (int i = 0; i < topScores.size(); i++) {
            if (i == 5) {
                break;
            }
            System.out.println((i + 1) + ". \t" + topScores.get(i).getPlayer() + " - " + topScores.get(i).getPoints() + " pts");
        }
        System.out.println();
    }

    public void printAverageRating(int averageRating) {
        System.out.println(colorMap.getTextColor(Color.MAGENTA) + "Average rating: " + averageRating + "★" + colorMap.getDefaultColor());
    }

    public void printComments(List<Comment> comments) {
        System.out.println(colorMap.getTextColor(Color.MAGENTA) + "Comments:" + colorMap.getDefaultColor());
        for (int i = 0; i < comments.size(); i++) {
            if (i == 5) {
                break;
            }
            var comment = comments.get(i);
            System.out.println(colorMap.getTextColor(Color.BLUE) + comment.getPlayer() + ": " + colorMap.getDefaultColor() + comment.getComment());
        }
        System.out.println();
    }

    public void printAboutGame() {
        System.out.println(colorMap.getTextColor(Color.BLUE) + "Block Puzzle is a game where you have to place blocks on the board to fill it completely.");
        System.out.println("You can place blocks on the board and remove them. The goal is to fill the board completely.");
        System.out.println("You can choose from 3 levels of difficulty or play a random puzzle.");
        System.out.println("You can also leave a comment and rating after you finish the game.");
        System.out.println("Let's play!" + colorMap.getDefaultColor() + "\n");
    }

    public void askPlayerNickname() {
        System.out.println("Please enter your nickname: ");
        System.out.print(colorMap.getTextColor(Color.MAGENTA) + "Nickname: " + colorMap.getDefaultColor());
    }

    public void printPlayerScore(int score) {
        System.out.println("Your score: " +  colorMap.getTextColor(Color.BLUE) + score + colorMap.getDefaultColor());
    }

    public void askPlayerForNextMove() {
        System.out.println(colorMap.getTextColor(Color.BLUE) + "What would you like to do?" + colorMap.getDefaultColor());
        System.out.println("1. Place a block");
        System.out.println("2. Remove a block");
        System.out.println("3. Surrender");
        System.out.println("Enter your choice (1, 2, or 3): ");
    }

    public void askOfGameLevel() {
        System.out.println(colorMap.getTextColor(Color.BLUE) + "\nPlease choose the game level:" + colorMap.getDefaultColor());
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("4. Random Puzzle");
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
        System.out.println("Type " + colorMap.getTextColor(Color.GREEN) + "'y'" + colorMap.getDefaultColor() + " to continue or " + colorMap.getTextColor(Color.RED) + "'n'" + colorMap.getDefaultColor() + " to exit.\n");
    }

    public void askLeaveComment() {
        System.out.println("Would you like to leave a comment?");
        System.out.println("Type " + colorMap.getTextColor(Color.GREEN) + "'y'" + colorMap.getDefaultColor() + " to leave a comment or " + colorMap.getTextColor(Color.RED) + "'n'" + colorMap.getDefaultColor() + " to skip.\n");
    }

    public void askLeaveRating() {
        System.out.println("Would you like to leave a rating?");
        System.out.println("Type " + colorMap.getTextColor(Color.GREEN) + "'y'" + colorMap.getDefaultColor() + " to leave a rating or " + colorMap.getTextColor(Color.RED) + "'n'" + colorMap.getDefaultColor() + " to skip.\n");
    }

    public void printCommentAdding() {
        System.out.print("Please enter your comment (max. 64 characters): ");
    }

    public void printRatingAdding() {
        System.out.print("Please enter your rating (1-5): ");
    }

    public void printCongratulations() {
        System.out.println("Congratulations!");
        System.out.println("You've completed the level successfully.\n");
    }
}
