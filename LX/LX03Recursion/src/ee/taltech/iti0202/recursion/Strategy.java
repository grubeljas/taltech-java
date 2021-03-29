/**
 * Info about this package.
 */
package ee.taltech.iti0202.recursion;
public interface Strategy {

    /**
     * Set number.
     *
     * @param number
     */
    void setNumber(int number);

    /**
     * Move opponent.
     *
     * @param x
     * @param y
     */
    void moveOpponent(int x, int y);

    /**
     * Get move.
     *
     * @return move.
     */
    int getMove();

}
