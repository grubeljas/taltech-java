package ee.taltech.iti0202.university;

import ee.taltech.iti0202.university.exception.CourseException;
import ee.taltech.iti0202.university.people.Student;
import ee.taltech.iti0202.university.subject.Course;

import java.util.LinkedList;
import java.util.List;

public class University {
    private List<Course> courseList;
    private List<Student> studentList;
    private String name;

    /**
     * Constructor.
     * @param name
     */
    public University(String name) {
        this.name = name;
        this.courseList = new LinkedList<>();
        this.studentList = new LinkedList<>();
    }

    /**
     * Add course if not in the university.
     * @param course
     * @return
     * @throws CourseException
     */
    public boolean addCourse(Course course) throws CourseException {
        try {
            if (courseList.contains(course)) {
                throw new CourseException(CourseException.Reason.ALREADY_IN_UNI);
            }
            courseList.add(course);
            return true;
        } catch (CourseException e) {
            System.out.println(e.getReason());
            return false;
        }
    }

    /**
     * Add courses.
     * @param courses
     * @throws CourseException
     */
    public void addCourse(List<Course> courses) throws CourseException {
        for (Course course: courses) {
            addCourse(course);
        }
    }

    /**
     * Add student calls from Student class.
     * @param student
     */
    public void addStudent(Student student) {
        studentList.add(student);
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public String getName() {
        return name;
    }
}
