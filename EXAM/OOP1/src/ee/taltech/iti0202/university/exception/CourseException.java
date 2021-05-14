package ee.taltech.iti0202.university.exception;

public class CourseException extends Exception {

    private Reason reason;

    /**
     * Constructor, throws exception about course, see reason.
     * @param reason
     */
    public CourseException(CourseException.Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }

    public enum Reason {
        ALREADY_IN_UNI, NOT_IN_UNI, INVALID_ASSESSMENT, COURSE_IS_PASSED,
        ACTIVE_SUBJECTS_IS_NOT_EMPTY, ALREADY_IN_DECLARATION
    }

}
