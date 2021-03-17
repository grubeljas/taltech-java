package ee.taltech.iti0202.coffee.machine;

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
        NOT_ENOUGH_WATER, WATER_IS_LEAKING
    }

    public Reason getReason() {
        return reason;
    }
}

