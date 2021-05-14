package ee.taltech.iti0202.university.subject;

import ee.taltech.iti0202.university.exception.StudentException;
import ee.taltech.iti0202.university.people.Student;

import java.util.LinkedList;
import java.util.List;

public class Course {

    private final List<Student> studentList;
    private int eap;
    private final String name;
    private CourseType courseType;
    private final boolean isAssessment;

    /**
     * Type of subject.
     */
    public enum CourseType {
        VABAÕPE, ERIALA, ÜLDÕPE
    }

    /**
     * Abstract constructor.
     * @param eap number of eap.
     * @param name of subject.
     */
    public Course(String name, int eap, boolean isAssessment, CourseType courseType) {
        this.eap = eap;
        this.name = name;
        this.studentList = new LinkedList<>();
        this.isAssessment = isAssessment;
        this.courseType = courseType;
    }

    /**
     * Add Student if not int the course.
     * @param student
     * @return
     */
    public boolean addStudent(Student student) {
        try {
            if (!studentList.contains(student)) {
                throw new StudentException(StudentException.Reason.ALREADY_IN_COURSE);
            }
            studentList.add(student);
            return true;
        } catch (StudentException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public int getEap() {
        return eap;
    }

    public void setEap(int eap) {
        this.eap = eap;
    }

    public String getName() {
        return name;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public boolean isAssessment() {
        return isAssessment;
    }
}
