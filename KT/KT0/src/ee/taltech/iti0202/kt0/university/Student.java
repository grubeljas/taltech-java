package ee.taltech.iti0202.kt0.university;
import java.util.ArrayList;
import java.util.List;

public class Student {
    String name;
    int eap;
    List<Course> unfinishedCourses;

    /**
     * Constructor.
     *
     * @param name
     */
    public Student(String name) {
        this.unfinishedCourses = new ArrayList<>();
        this.name = name;
        this.eap = 0;
    }

    public String getName() {
        return name;
    }

    public int getEap() {
        return eap;
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", name, eap);
    }
}
