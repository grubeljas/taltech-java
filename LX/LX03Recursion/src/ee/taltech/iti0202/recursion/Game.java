package ee.taltech.iti0202.recursion;
public class Game {

    private Strategy[] players;
    private Board board;

    public Game(Strategy playerA, Strategy playerB) {
        this.players = new Strategy[2];
        this.players[0] = playerA;
        this.players[0].setNumber(1);
        this.players[1] = playerB;
        this.players[1].setNumber(2);
        board = new Board();
    }

    public int play() {
        for (int i = 0; i < 9; i++) {
            System.out.println(board.toStr());
            int move = this.players[i % 2].getMove();
            // System.out.println(move);
            board.move(move / 3, move % 3);
            // System.out.println(board.toStr());
            this.players[(i + 1) % 2].moveOpponent(move / 3, move % 3);
            if (board.getWinner() != 0) {
                System.out.println(board.toStr());
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
