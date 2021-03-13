package ee.taltech.iti0202.coffee;

public class CoffeeException extends Exception{

    public Reason reason;

    /**
     * Constructor.
     *
     * @param reason reason from enum.
     */
    public CoffeeException(Reason reason) {
        this.reason = reason;
    }

    public enum Reason {
        NOT_ENOUGH_COFFEE, NOT_ENOUGH_WATER
    }

    public Reason getReason() {
        return reason;
    }
}

