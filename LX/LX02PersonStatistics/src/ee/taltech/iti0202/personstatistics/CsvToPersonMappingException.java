package ee.taltech.iti0202.personstatistics;

public class CsvToPersonMappingException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message
     * @param cause
     */
    public CsvToPersonMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
