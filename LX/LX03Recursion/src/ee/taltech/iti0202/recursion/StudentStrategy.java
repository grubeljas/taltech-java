package ee.taltech.iti0202.recursion;
import java.util.Random;
public class StudentStrategy implements Strategy {

    private int number, opponent;
    private Board board;
    private Random rand;
    private static final int inf = 100;
    static final int THREE = 3;
    static final int SQUARES = 9;

    /**
     * Constructor.
     *
     */
    public StudentStrategy() {
        this.board = new Board();
        this.rand = new Random();
    }

    /**
     * Set who is player.
     *
     * @param number
     */
    @Override
    public void setNumber(int number) {
        this.number = number;
        this.opponent = number == 1 ? 2 : 1;
        System.out.println("me:" + number + " Opponent:" + opponent);
    }

    /**
     * Move opponent.
     *
     * @param x
     * @param y
     */
    @Override
    public void moveOpponent(int x, int y) {
        this.board.move(x, y);
    }

    /**
     * Get move.
     *
     * @return move.
     */
    @Override
    public int getMove() {
        Board copiedBoard = new Board(board);
        int move;
        if (board.getMoveCounter() == 0) {
            System.out.println("rand");
            move = rand.nextInt(SQUARES);
        } else {
            move = getBestMove(copiedBoard);
        }
        this.board.move(move / THREE, move % THREE);
        return move;
    }

    /**
     * Get best move.
     *
     * @param board
     * @return number of square.
     */
    public int getBestMove(Board board) {
        int bestMove = 0;
        int bestResult = -10;
        int optionalResult;
        for (int i = 0; i < SQUARES; i++) {
            if(board.isLegal(i / THREE, i % THREE)) {
                Board board1 = new Board(board);
                board1.move(i / THREE, i % THREE);
                optionalResult = getMinValue(board1);
                if (optionalResult > bestResult) {
                    bestResult = optionalResult;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    /**
     * Get best move for opponent.
     *
     * @param board
     * @return
     */
    public int getMinValue(Board board) {
        int bestResult = inf;
        int optionalResult;
        for (int i = 0; i < SQUARES; i++) {
            if(board.isLegal(i / THREE, i % THREE)) {
                Board board2 = new Board(board);
                board2.move(i / THREE, i % THREE);
                if (board2.getWinner() == opponent) {
                    optionalResult = -1 * (SQUARES + 1 - board2.getMoveCounter());
                } else if (board2.isFull()) {
                    optionalResult = 0;
                } else {
                    optionalResult = getMaxValue(board2);
                }
                if (optionalResult < bestResult) {
                    bestResult = optionalResult;
                }
            }
        }
        return bestResult;
    }

    /**
     * Get best move for you.
     *
     * @param board
     * @return
     */
    public int getMaxValue(Board board) {
        int bestResult = -inf;
        int optionalResult;
        for (int i = 0; i < SQUARES; i++) {
            if (board.isLegal(i / THREE, i % THREE)) {
                Board board2 = new Board(board);
                board2.move(i / THREE, i % THREE);
                if (board2.getWinner() == number) {
                    optionalResult = 1 * (SQUARES + 1 - board2.getMoveCounter());
                } else if (board2.isFull()) {
                    optionalResult = 0;
                } else {
                    optionalResult = getMinValue(board2);
                }
                if (optionalResult > bestResult) {
                    bestResult = optionalResult;
                }
            }
        }
        return bestResult;
    }
}
