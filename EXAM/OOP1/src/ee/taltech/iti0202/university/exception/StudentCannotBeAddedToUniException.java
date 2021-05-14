package ee.taltech.iti0202.university.exception;

public class StudentCannotBeAddedToUniException extends Exception{

    private Reason reason;

    public StudentCannotBeAddedToUniException(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }

    public enum Reason {
        UNDER_18, ALREADY_IN_UNI
    }

}
