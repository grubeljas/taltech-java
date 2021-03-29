/**
 * Info about this package.
 */
package ee.taltech.iti0202.recursion;
public class Board {

    /**
     * Field with board and moves.
     *
     */
    private int[][] board;
    private int nextMove;
    private int moveCounter;
    static final int THREE = 3;
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
     * @param board
     */
    public Board(Board board) {
        this.board = new int[THREE][THREE];
        int[][] otherBoard = board.getBoard();
        for (int y = 0; y < THREE; y++) {
            for (int x = 0; x < THREE; x++) {
                this.board[x][y] = otherBoard[x][y];
            }
        }
        nextMove = board.getNextMove();
        moveCounter = board.getMoveCounter();
    }

    /**
     * Get how many move was made.
     *
     * @return
     */
    public int getMoveCounter() {
        return moveCounter;
    }

    /**
     * Is possible move.
     *
     * @param move
     * @return
     */
    public boolean isLegal(int move) {
        return this.board[move / THREE][move % THREE] == 0;
    }

    /**
     * Is legal this turn.
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isLegal(int x, int y) {
        return this.board[x][y] == 0;
    }

    /**
     * Get this board.
     *
     * @return
     */
    public int[][] getBoard() {
        return board;
    }

    public int getNextMove() {
        return nextMove;
    }

    /**
     * Show board.
     *
     * @return
     */
    public String toStr() {
        StringBuilder string = new StringBuilder();
        for (int y = 0; y < THREE; y++) {
            for (int x = 0; x < THREE; x++) {
                string.append(board[y][x] == 1 ? "X" : (board[y][x] == 2 ? "O" : "_"));
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
    public void move(int x, int y) {
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
     * @return
     */
    public boolean isFull() {
        return moveCounter >= SQUARES;
    }

    /**
     * Is somebody won.
     *
     * @return
     */
    public int getWinner() {
        for (int i = 0; i < THREE; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) {
                return board[i][0];
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        if (board[0][0] != 0 && board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return board[1][1];
        }
        if (board[2][0] != 0 && board[2][0] == board[1][1] &&
                board[1][1] == board[0][2]) {
            return board[1][1];
        }
        return 0;
    }
}
