/**
 * Info about this package.
 */
package ee.taltech.iti0202.recursion;
import java.util.Random;

public class RandomStrategy implements Strategy {

    /** Number of player
     *
     */
    private int number;
    /**
     * This board.
     */
    private Board board;
    /**
     * Random lib.
     */
    private Random random;
    /**
     * Three in a row.
     */
    static final int THREE = 3;
    /**
     * Nine squares.
     */
    static final int SQUARES = 9;

    /**
     * Constructor.
     *
     */
    public RandomStrategy() {
        board = new Board();
        random = new Random();
    }

    /**
     * Set number.
     *
     * @param number1
     */
    @Override
    public void setNumber(int number1) {
        this.number = number1;
    }

    /**
     * Move opponent.
     *
     * @param x
     * @param y
     */
    @Override
    public void moveOpponent(final int x, final int y) {
        board.move(x, y);
    }

    /**
     * Get random move.
     *
     * @return
     */
    @Override
    public int getMove() {
        while (true) {
            int move = random.nextInt(SQUARES);
            if (board.getBoard()[move / THREE][move % THREE] == 0) {
                board.move(move / THREE, move % THREE);
                return move;
            }
        }
    }
}
