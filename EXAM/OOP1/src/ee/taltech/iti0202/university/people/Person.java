package ee.taltech.iti0202.university.people;

import ee.taltech.iti0202.university.subject.Course;

import java.util.LinkedList;
import java.util.List;

public class Person {

    private final String name;
    private int age;
    private final List<Course> activeCourses;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.activeCourses = new LinkedList<>();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public List<Course> getActiveCourses() {
        return activeCourses;
    }
}