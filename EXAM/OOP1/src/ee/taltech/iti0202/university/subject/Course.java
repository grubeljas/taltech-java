package ee.taltech.iti0202.university.subject;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exception.CourseException;
import ee.taltech.iti0202.university.exception.StudentException;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.people.Teacher;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Course {

    private final List<Student> studentList;
    private int eap;
    private final String name;
    private CourseType courseType;
    private final boolean isGrade;
    private University university;
    private Optional<Teacher> teacher;

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
     * @param isGrade
     * @param courseType
     */
    public Course(String name, int eap, boolean isGrade, CourseType courseType) {
        this.eap = eap;
        this.name = name;
        this.studentList = new LinkedList<>();
        this.isGrade = isGrade;
        this.courseType = courseType;
        this.teacher = Optional.empty();
    }

    /**
     * Same constructor but with university and teacher.
     * @param name
     * @param eap
     * @param isGrade
     * @param courseType
     * @param university
     */
    public Course(String name, int eap, boolean isGrade, CourseType courseType, University university, Teacher teacher)
            throws CourseException {
        this.eap = eap;
        this.name = name;
        this.studentList = new LinkedList<>();
        this.isGrade = isGrade;
        this.courseType = courseType;
        this.teacher = Optional.of(teacher);
        teacher.getActiveCourses().add(this);
        setUniversity(university);
        university.addCourse(this);
    }

    /**
     * Add Student if not int the course.
     * @param student
     * @return
     */
    public boolean addStudent(Student student) throws StudentException {
        try {
            if (studentList.contains(student)) {
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

    public boolean isGrade() {
        return isGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Optional<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = Optional.of(teacher);
    }

    public University getUniversity() {
        return university;
    }

    public final void setUniversity(University university) {
        this.university = university;
    }
}
