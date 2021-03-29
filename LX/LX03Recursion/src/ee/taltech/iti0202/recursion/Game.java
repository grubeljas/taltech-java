// This package has non null parameters and is documented.
package ee.taltech.iti0202.recursion;

public final class Game {

    /**
     * Field with board with players.
     */
    private Strategy[] players;
    /**
     * Bruh.
     */
    private Board board;
    /**
     * Bruh.
     */
    static final int THREE = 3;
    /**
     * Bruh.
     */
    static final int SQUARES = 9;

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
        for (int i = 0; i < SQUARES; i++) {
            System.out.println(board.toStr());
            int move = this.players[i % 2].getMove();
            // System.out.println(move);
            board.move(move / THREE, move % THREE);
            // System.out.println(board.toStr());
            this.players[(i + 1) % 2].moveOpponent(move / THREE, move % THREE);
            if (board.getWinner() != 0) {
                return board.getWinner();
            }
        }
        return 0;
    }

}
