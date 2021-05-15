package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.exception.CourseException;
import ee.taltech.iti0202.university.exception.StudentException;
import ee.taltech.iti0202.university.people.Teacher;
import ee.taltech.iti0202.university.subject.Course;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TeacherDeclarationTest {

    private final int age = 33;

    /**
     * Ei jõudnud teha teste.
     * @throws CourseException
     */
    @Test
    public void teacherTest() throws CourseException, StudentException {
        University ttu = new University("TTU");
        Teacher ago = new Teacher("Ago", age, ttu);
        Teacher tammel = new Teacher("Tammel", age, ttu);
        Course programmingBeggining = new Course("Programmeerimine algkursus",
                6,
                true,
                Course.CourseType.ERIALA);
        Course diskreetneMatemaatika = new Course("Diskreetne matemaatika",
                6,
                true,
                Course.CourseType.ERIALA);
        Course sissejuhatusIT = new Course("Sissejuhatus ITsse",
                6,
                false,
                Course.CourseType.ERIALA);
        Course physics = new Course("Füüsika alused I",
                3,
                false,
                Course.CourseType.VABAÕPE,
                ttu,
                ago);
        Course fakeProgramming = new Course("Programmeerimine algkursus",
                3,
                true,
                Course.CourseType.ERIALA);
        ttu.addCourse(List.of(programmingBeggining, diskreetneMatemaatika, sissejuhatusIT));
        ttu.changeTeacherOfCourse(programmingBeggining, ago);
        ttu.changeTeacherOfCourse(diskreetneMatemaatika, ago);
        ttu.changeTeacherOfCourse(sissejuhatusIT, tammel);

        assert ago.getUniversity().equals(ttu);
        assert tammel.getActiveCourses().size() == 1;
        assert ago.getActiveCourses().size() == 3;

        ttu.changeTeacherOfCourse(diskreetneMatemaatika, tammel);
        assert tammel.getActiveCourses().size() == 2;
        assert ago.getActiveCourses().size() == 2;
    }
}
