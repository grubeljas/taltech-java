package ee.taltech.iti0202.recursion;
public class Board {
    private int[][] board;
    private int nextMove;
    private int moveCounter;

    /**
     * Construct.
     *
     */
    public Board() {
        board = new int[Game.tHREE][Game.tHREE];
        for (int y = 0; y < Game.tHREE; y++) {
            for (int x = 0; x < Game.tHREE; x++) {
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
        this.board = new int[Game.tHREE][Game.tHREE];
        int[][] otherBoard = board.getBoard();
        for (int y = 0; y < Game.tHREE; y++) {
            for (int x = 0; x < Game.tHREE; x++) {
                this.board[x][y] = otherBoard[x][y];
            }
        }
        nextMove = board.getNextMove();
        moveCounter = board.getMoveCounter();
    }

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
        return this.board[move / Game.tHREE][move % Game.tHREE] == 0;
    }

    /**
     * I
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isLegal(int x, int y) {
        return this.board[x][y] == 0;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getNextMove() {
        return nextMove;
    }

    public String toStr() {
        StringBuilder string = new StringBuilder();
        for (int y = 0; y < Game.tHREE; y++) {
            for (int x = 0; x < Game.tHREE; x++) {
                string.append(board[y][x] == 1 ? "X" : (board[y][x] == 2 ? "O" : "_"));
            }
            string.append("\n");
        }
        return string.toString();
    }

    public void move(int x, int y) {
        if (board[x][y] != 0) {
            throw new IllegalMoveException(x, y);
        }
        board[x][y] = nextMove;
        nextMove = nextMove == 1 ? 2 : 1;
        moveCounter += 1;
    }

    public boolean isFull() {
        return moveCounter >= Game.numberOfSquares;
    }

    public int getWinner() {
        for (int i = 0; i < Game.tHREE; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[1][1];
        }
        if (board[2][0] != 0 && board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            return board[1][1];
        }
        return 0;
    }
}
