package ee.taltech.iti0202.recursion;
import java.util.Random;

public class RandomStrategy implements Strategy {

    private int number;
    private Board board;
    private Random random;
    static final int tHREE = 3;
    static final int numberOfSquares = 9;

    public RandomStrategy() {
        board = new Board();
        random = new Random();
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void moveOpponent(int x, int y) {
        board.move(x, y);
    }

    @Override
    public int getMove() {
        while (true) {
            int move = random.nextInt(numberOfSquares);
            if (board.getBoard()[move / tHREE][move % tHREE] == 0) {
                board.move(move / tHREE, move % tHREE);
                return move;
            }
        }
    }
}
