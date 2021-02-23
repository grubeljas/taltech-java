package ee.taltech.iti0202.mysticorbs.oven;
import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;

public interface Fixable {

    /**
     * Fix an oven.
     *
     * @throws CannotFixException
     */
    void fix() throws CannotFixException;

    /**
     * Get number of repairs.
     *
     * @return n.
     */
    int getTimesFixed();
}
