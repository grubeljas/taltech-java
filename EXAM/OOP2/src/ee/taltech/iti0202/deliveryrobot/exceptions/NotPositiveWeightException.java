package ee.taltech.iti0202.deliveryrobot.exceptions;

public class NotPositiveWeightException extends Exception{

    /**
     * Constructor.
     * @param message
     * @param cause
     */
    public NotPositiveWeightException(String message, Throwable cause) {
        super(message, cause);
    }

}
