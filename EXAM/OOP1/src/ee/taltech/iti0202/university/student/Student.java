package ee.taltech.iti0202.university.student;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exception.StudentCannotBeAddedToUniException;

import java.util.Optional;

public class Student {

    private String name;
    private int age;
    Optional<Object> studiedIn;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
        this.studiedIn = Optional.empty();
    }

    public boolean goToStudy(University university) throws StudentCannotBeAddedToUniException{
        try {
            if (age < 18) {
                throw new StudentCannotBeAddedToUniException(StudentCannotBeAddedToUniException.Reason.UNDER_18);
            } else if (studiedIn.isPresent()) {
                throw new StudentCannotBeAddedToUniException(StudentCannotBeAddedToUniException.Reason.ALREADY_IN_UNI);
            }
            studiedIn = Optional.of(university);
            return true;
        } catch (StudentCannotBeAddedToUniException e) {
            System.out.println(name + e.getReason());
            return false;
        }
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
}
