package ee.taltech.iti0202.recursion;
import java.util.Random;

public class RandomStrategy implements Strategy {

    private int number;
    private Board board;
    private Random random;

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
            int move = random.nextInt(9);
            if (board.getBoard()[move / 3][move % 3] == 0) {
                board.move(move / 3, move % 3);
                return move;
            }
        }
    }
}
