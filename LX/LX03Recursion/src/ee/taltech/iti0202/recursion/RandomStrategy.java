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
            if (board.getBoard()[move / Game.tHREE][move % Game.tHREE] == 0) {
                board.move(move / Game.tHREE, move % Game.tHREE);
                return move;
            }
        }
    }
}
