package ee.taltech.iti0202.kt0.university;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class University {
    String name;
    List<Student> students;
    List<Course> courses;

    public University(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            return true;
        }
        return false;
    }

    public Optional<Course> createCourse(String name, int eap) {
        if (eap > 0 && !name.isBlank()) {
            for (Course c: courses) {
                if (c.name.equals(name)) {
                    return Optional.empty();
                }
            }
            Course course = new Course(name, eap, this);
            courses.add(course);
            return Optional.of(course);
        }
        return Optional.empty();
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getFinishedCourses() {
        List<Course> finished = courses.stream()
                .filter(Course::isFinished)
                .collect(Collectors.toList());
        return finished;
    }

    public List<Student> getStudentsOrderedByResults() {
        List<Student> ordered = students.stream()
                .sorted(Comparator.comparing(Student::getEap))
                .collect(Collectors.toList());
        return ordered;

    }
}
