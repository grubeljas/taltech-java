/**
 * Info about this package.
 */
package ee.taltech.iti0202.recursion;

public class Board {

    /**
     * Field with board and moves.
     */
    private int[][] board;
    /**
     * Bruh.
     */
    private int nextMove;
    /**
     * Bruh.
     */
    private int moveCounter;
    /**
     * Bruh.
     */
    static final int THREE = 3;
    /**
     * Bruh.
     */
    static final int SQUARES = 9;

    /**
     * Construct.
     *
     */
    public Board() {
        board = new int[THREE][THREE];
        for (int y = 0; y < THREE; y++) {
            for (int x = 0; x < THREE; x++) {
                board[x][y] = 0;
            }
        }
        nextMove = 1;
        moveCounter = 0;
    }

    /**
     * Constructor but epic.
     *
     * @param board1
     */
    public Board(final Board board1) {
        this.board = new int[THREE][THREE];
        int[][] otherBoard = board1.getBoard();
        for (int y = 0; y < THREE; y++) {
            for (int x = 0; x < THREE; x++) {
                this.board[x][y] = otherBoard[x][y];
            }
        }
        nextMove = board1.getNextMove();
        moveCounter = board1.getMoveCounter();
    }

    /**
     * Get how many move was made.
     *
     * @return how many moves were.
     */
    public int getMoveCounter() {
        return moveCounter;
    }

    /**
     * Is possible move.
     *
     * @param move
     * @return is possible move.
     */
    public boolean isLegal(final int move) {
        return this.board[move / THREE][move % THREE] == 0;
    }

    /**
     * Is legal this turn.
     *
     * @param x
     * @param y
     * @return is possible move.
     */
    public boolean isLegal(final int x, final int y) {
        return this.board[x][y] == 0;
    }

    /**
     * Get this board.
     *
     * @return board.
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Get who's gonna move O or X.
     *
     * @return next move.
     */
    public int getNextMove() {
        return nextMove;
    }

    /**
     * Show board.
     *
     * @return game to string.
     */
    public String toStr() {
        StringBuilder string = new StringBuilder();
        for (int y = 0; y < THREE; y++) {
            for (int x = 0; x < THREE; x++) {
                string.append(board[y][x] == 1 ? "X"
                        : (board[y][x] == 2 ? "O" : "_"));
            }
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Move x or o.
     *
     * @param x
     * @param y
     */
    public void move(final int x, final int y) {
        if (board[x][y] != 0) {
            throw new IllegalMoveException(x, y);
        }
        board[x][y] = nextMove;
        nextMove = nextMove == 1 ? 2 : 1;
        moveCounter += 1;
    }

    /**
     * Check if board is full.
     *
     * @return is game is full.
     */
    public boolean isFull() {
        return moveCounter >= SQUARES;
    }

    /**
     * Is somebody won.
     *
     * @return who is winner.
     */
    public int getWinner() {
        for (int i = 0; i < THREE; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1]
                    && board[i][1] == board[i][2]) {
                return board[i][0];
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i]
                    && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        if (board[0][0] != 0 && board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) {
            return board[1][1];
        }
        if (board[2][0] != 0 && board[2][0] == board[1][1]
                && board[1][1] == board[0][2]) {
            return board[1][1];
        }
        return 0;
    }
}
