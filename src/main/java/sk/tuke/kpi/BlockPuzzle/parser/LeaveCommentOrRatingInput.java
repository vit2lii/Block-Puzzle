package sk.tuke.kpi.BlockPuzzle.parser;

public enum LeaveCommentOrRatingInput {
    LEAVE(1),
    INVALID(-1);

    private final int input;

    LeaveCommentOrRatingInput(int input) {
        this.input = input;
    }

    public static LeaveCommentOrRatingInput getLeaveCommentOrRatingInput(int input) {
        return switch (input) {
            case 1 -> LeaveCommentOrRatingInput.LEAVE;
            default -> LeaveCommentOrRatingInput.INVALID;
        };
    }
}
