package ee.taltech.iti0202.recursion;
import java.util.Random;

public class RandomStrategy implements Strategy {

    private int number;
    private Board board;
    private Random random;
    static final int THREE = 3;
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
     * @param number
     */
    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Move opponent.
     *
     * @param x
     * @param y
     */
    @Override
    public void moveOpponent(int x, int y) {
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
