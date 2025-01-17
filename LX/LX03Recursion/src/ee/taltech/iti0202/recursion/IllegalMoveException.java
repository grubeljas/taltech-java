/**
 * Info about this package.
 */
package ee.taltech.iti0202.recursion;
public class IllegalMoveException extends RuntimeException {

    /**
     * Constructor exception.
     *
     * @param x
     * @param y
     */
    public IllegalMoveException(final int x, final int y) {
        super("There is already a button at (" + x + "; " + y + ")");
    }
}
