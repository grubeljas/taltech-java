package ee.taltech.iti0202.recursion;

public final class Game {

    private Strategy[] players;
    private Board board;
    static final int tHREE = 3;
    static final int numberOfSquares = 9;

    /**
     * Constructor.
     *
     * @param playerA
     * @param playerB
     */
    public Game(final Strategy playerA, final Strategy playerB) {
        this.players = new Strategy[2];
        this.players[0] = playerA;
        this.players[0].setNumber(1);
        this.players[1] = playerB;
        this.players[1].setNumber(2);
        board = new Board();
    }

    /**
     * Play add moves to other player.
     *
     * @return result of game.
     */
    public int play() {
        for (int i = 0; i < numberOfSquares; i++) {
            System.out.println(board.toStr());
            int move = this.players[i % 2].getMove();
            // System.out.println(move);
            board.move(move / tHREE, move % tHREE);
            // System.out.println(board.toStr());
            this.players[(i + 1) % 2].moveOpponent(move / tHREE, move % tHREE);
            if (board.getWinner() != 0) {
                return board.getWinner();
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Strategy b = new RandomStrategy();
        Strategy a = new StudentStrategy();
        Game game = new Game(b, a);
        System.out.println(game.play());
    }
}
