package ee.taltech.iti0202.university.exception;

public class StudentException extends Exception {

    private Reason reason;

    /**
     * Constructor, throws only in goToStudy method.
     * @param reason
     */
    public StudentException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }

    public enum Reason {
        UNDER_18, ALREADY_IN_UNI, ALREADY_IN_COURSE
    }

}
