package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.Main.Main;
import ee.taltech.iti0202.university.exception.CourseException;
import ee.taltech.iti0202.university.exception.StudentException;
import org.junit.jupiter.api.Test;

public class MainTest {

    /**
     * Main test.
     * @throws CourseException
     * @throws StudentException
     */
    @Test
    public void mainTest() throws CourseException, StudentException {
        String[] args = null;
        Main.main(args);
    }
}
