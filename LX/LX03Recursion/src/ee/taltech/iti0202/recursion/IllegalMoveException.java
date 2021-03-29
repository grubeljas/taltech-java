package ee.taltech.iti0202.recursion;
public class IllegalMoveException extends RuntimeException {
    public IllegalMoveException(int x, int y) {
        super("There is already a button at (" + x + "; " + y + ")");
    }
}
