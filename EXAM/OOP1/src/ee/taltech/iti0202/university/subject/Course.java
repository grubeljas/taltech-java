package ee.taltech.iti0202.university.subject;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exception.CourseException;
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
    private University university;

    /**
     * Type of subject.
     */
    public enum CourseType {
        VABAÕPE, ERIALA, ÜLDÕPE
    }

    /**
     * Constructor.
     * @param name of course
     * @param eap
     * @param isAssessment
     * @param courseType
     */
    public Course(String name, int eap, boolean isAssessment, CourseType courseType) {
        this.eap = eap;
        this.name = name;
        this.studentList = new LinkedList<>();
        this.isAssessment = isAssessment;
        this.courseType = courseType;
    }

    /**
     * Same constructor but with university.
     * @param name
     * @param eap
     * @param isAssessment
     * @param courseType
     * @param university
     */
    public Course(String name, int eap, boolean isAssessment, CourseType courseType, University university)
            throws CourseException {
        this.eap = eap;
        this.name = name;
        this.studentList = new LinkedList<>();
        this.isAssessment = isAssessment;
        this.courseType = courseType;
        setUniversity(university);
        university.addCourse(this);
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

    public University getUniversity() {
        return university;
    }

    public final void setUniversity(University university) {
        this.university = university;
    }
}
