package ee.taltech.iti0202.kt0.university;

import java.util.ArrayList;
import java.util.List;

public class Course {
    List<Student> students;
    boolean isFinished;
    University uni;
    int eap;
    String name;

    public Course(String name, int eap, University uni) {
        this.eap = eap;
        this.uni = uni;
        this.name = name;
        this.students = new ArrayList<>();
        this.isFinished = false;
    }

    public boolean addStudent(Student student) {
        if (students.contains(student)) {
            return false;
        }
        students.add(student);
        student.unfinishedCourses.add(this);
        return true;
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean finish() {
        if (!isFinished) {
            isFinished = true;
            for (Student s: students) {
                s.eap += eap;
                s.unfinishedCourses.remove(this);
            }
            return true;
        }
        return false;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public String toString() {
        return String.format("%1$s: %2$s (%3$d)", uni.name, name, eap);
    }
}
