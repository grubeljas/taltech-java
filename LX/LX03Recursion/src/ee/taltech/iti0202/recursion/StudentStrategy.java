package ee.taltech.iti0202.recursion;
import java.util.Random;
public class StudentStrategy implements Strategy {

    private int number, opponent;
    private Board board;
    private Random rand;

    public StudentStrategy() {
        this.board = new Board();
        this.rand = new Random();
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
        this.opponent = number == 1 ? 2 : 1;
        System.out.println("me:" + number + " Opponent:" + opponent);
    }

    @Override
    public void moveOpponent(int x, int y) {
        this.board.move(x, y);
    }

    @Override
    public int getMove() {
        Board copiedBoard = new Board(board);
        int move;
        if (board.getMoveCounter() == 0) {
            System.out.println("rand");
            move = rand.nextInt(9);
        } else {
            move = getBestMove(copiedBoard);
        }
        this.board.move(move / 3, move % 3);
        return move;
    }

    public int getBestMove(Board board) {
        int bestMove = 0;
        int bestResult = -10;
        int optionalResult;
        for (int i = 0; i < 9; i++) {
            if(board.isLegal(i / 3, i % 3)) {
                Board board1 = new Board(board);
                board1.move(i / 3, i % 3);
                optionalResult = getMinValue(board1);
                System.out.println(optionalResult);
                if (optionalResult > bestResult) {
                    bestResult = optionalResult;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    public int getMinValue(Board board) {
        // Iterate through all legal moves, recursively (using getMaxValue) get value of each move and return the min of those values
        int bestResult = 100;
        int optionalResult;
        for (int i = 0; i < 9; i++) {
            if(board.isLegal(i / 3, i % 3)) {
                Board board2 = new Board(board);
                board2.move(i / 3, i % 3);
                if (board2.getWinner() == opponent) {
                    optionalResult = -1 * (10 - board2.getMoveCounter());
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

    public int getMaxValue(Board board) {
        // Iterate through all legal moves, recursively (using getMinValue) get value of each move and return the max of those values
        int bestResult = -100;
        int optionalResult;
        for (int i = 0; i < 9; i++) {
            if(board.isLegal(i / 3, i % 3)) {
                Board board2 = new Board(board);
                board2.move(i / 3, i % 3);
                if (board2.getWinner() == number) {
                    optionalResult = 1 * (10 - board2.getMoveCounter());
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
