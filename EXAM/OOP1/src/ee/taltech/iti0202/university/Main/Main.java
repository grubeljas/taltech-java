package ee.taltech.iti0202.university.Main;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exception.CourseException;
import ee.taltech.iti0202.university.exception.StudentException;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.subject.Course;

import java.util.List;

public class Main {

    private static final int TEN = 10;
    private static final int MAJORITY = 18;

    /**
     * Main method all primitive thigs.
     * @param args
     * @throws StudentException
     * @throws CourseException
     */
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
        Course physics = new Course("Füüsika alused I",
                3,
                false,
                Course.CourseType.VABAÕPE);
        Course fakeProgramming = new Course("Programmeerimine algkursus",
                3,
                true,
                Course.CourseType.ERIALA);
        ttu.addCourse(List.of(programmingBeggining, diskreetneMatemaatika, sissejuhatusIT, physics));
        assert ttu.getCourseList().size() == 4;
        System.out.println(ttu.getCourseList().size());
        assert ttu.addCourse(fakeProgramming) == false;

        for (int i = 0; i < TEN; i++) {
            Student student = new Student("bot" + i, MAJORITY + i);
            student.goToStudy(ttu);
        }
        assert ttu.getStudentList().size() == 10;
        System.out.println(ttu.getStudentList());

        Student student = ttu.getStudentList().get(0);

        assert !student.goToStudy(tartuUni);
        assert tartuUni.getStudentList().size() == 0;

        for (Course course: ttu.getCourseList()) {
            System.out.println("decl" + student.getDeclaration());
            student.addCourse(course);
        }
        assert student.getDeclaration().size() == 4;
        student.declareSubjects();
        assert student.getDeclaration().size() == 0;
        assert student.getActiveCourses().size() == 4;

        student.getGrade(programmingBeggining, 3);
        student.getGrade(diskreetneMatemaatika, 0);
        student.getGrade(sissejuhatusIT, 1);
        student.getGrade(physics, 0);
        assert student.getFinishedGrade().get(programmingBeggining) == 3;
        assert student.getFinishedGrade().get(diskreetneMatemaatika) == 0;
        assert student.getFinishedCredits().get(sissejuhatusIT);
        assert student.getFinishedCredits().get(physics) == false;

        System.out.println("History:" + student.getFinishedGrade() + student.getFinishedCredits());
    }
}
