package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.exception.CourseException;
import ee.taltech.iti0202.university.student.Student;
import ee.taltech.iti0202.university.subject.Aine;

import java.util.LinkedList;
import java.util.List;

public class University {
    private List<Aine> aineList;
    private List<Student> studentList;
    private String name;

    /**
     * Constructor.
     * @param name
     */
    public University(String name) {
        this.name = name;
        this.aineList = new LinkedList<>();
        this.studentList = new LinkedList<>();
    }

    /**
     * Add course if not in the university.
     * @param aine
     * @return
     * @throws CourseException
     */
    public boolean addCourse(Aine aine) throws CourseException {
        try {
            if (aineList.contains(aine)) {
                throw new CourseException(CourseException.Reason.ALREADY_IN_UNI);
            }
            aineList.add(aine);
            return true;
        } catch (CourseException e) {
            System.out.println(e.getReason());
            return false;
        }
    }

    /**
     * Add student.
     * @param student
     */
    public void addStudent(Student student) {
        studentList.add(student);
    }

    public List<Aine> getAineList() {
        return aineList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public String getName() {
        return name;
    }
}
