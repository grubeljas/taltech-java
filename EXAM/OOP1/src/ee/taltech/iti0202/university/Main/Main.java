package ee.taltech.iti0202.university.Main;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exception.CourseException;
import ee.taltech.iti0202.university.exception.StudentException;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.subject.Course;

import java.util.LinkedList;
import java.util.List;

public class Main {

    private static final int EAPNUMBER = 6;
    private static final int TEN = 10;
    private static final int MAJORITY = 18;

    public static void main(String[] args) throws StudentException, CourseException {
        University ttu = new University("TTU");
        University tartuUni = new University("TU");
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
        Course füüsika = new Course("Füüsika alused I",
                3,
                true,
                Course.CourseType.VABAÕPE);
        ttu.addCourse(List.of(programmingBeggining, diskreetneMatemaatika, sissejuhatusIT, füüsika));

        for (int i = 0; i < TEN; i++) {
            Student student = new Student("bot" + i, MAJORITY + i);
            student.goToStudy(ttu);
            System.out.println(student.getStudiedIn().isPresent());
            student.declareCourse(ttu.getCourseList());
        }
        ttu.getStudentList().get(0).goToStudy(tartuUni);

        System.out.println(ttu.getStudentList().size()); //10
        System.out.println(tartuUni.getStudentList().size()); //0
    }
}
