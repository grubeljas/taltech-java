/**
 * Info about this package.
 */
package ee.taltech.iti0202.recursion;
public interface Strategy {

    void setNumber(int number);
    void moveOpponent(int x, int y);
    int getMove();
}
