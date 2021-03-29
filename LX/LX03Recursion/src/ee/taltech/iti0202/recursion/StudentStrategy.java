package ee.taltech.iti0202.recursion;
import java.util.Random;
public class StudentStrategy implements Strategy {

    private int number, opponent;
    private Board board;
    private Random rand;
    private static final int inf = 100;
    static final int tHREE = 3;
    static final int numberOfSquares = 9;

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
            move = rand.nextInt(numberOfSquares);
        } else {
            move = getBestMove(copiedBoard);
        }
        this.board.move(move / tHREE, move % tHREE);
        return move;
    }

    public int getBestMove(Board board) {
        int bestMove = 0;
        int bestResult = -10;
        int optionalResult;
        for (int i = 0; i < numberOfSquares; i++) {
            if(board.isLegal(i / tHREE, i % tHREE)) {
                Board board1 = new Board(board);
                board1.move(i / tHREE, i % tHREE);
                optionalResult = getMinValue(board1);
                if (optionalResult > bestResult) {
                    bestResult = optionalResult;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    public int getMinValue(Board board) {
        int bestResult = inf;
        int optionalResult;
        for (int i = 0; i < numberOfSquares; i++) {
            if(board.isLegal(i / tHREE, i % tHREE)) {
                Board board2 = new Board(board);
                board2.move(i / tHREE, i % tHREE);
                if (board2.getWinner() == opponent) {
                    optionalResult = -1 * (numberOfSquares + 1 - board2.getMoveCounter());
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
        int bestResult = -inf;
        int optionalResult;
        for (int i = 0; i < numberOfSquares; i++) {
            if (board.isLegal(i / tHREE, i % tHREE)) {
                Board board2 = new Board(board);
                board2.move(i / tHREE, i % tHREE);
                if (board2.getWinner() == number) {
                    optionalResult = 1 * (numberOfSquares + 1 - board2.getMoveCounter());
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
