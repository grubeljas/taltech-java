package ee.taltech.iti0202.university.subject;

import ee.taltech.iti0202.university.student.Student;

import java.util.LinkedList;
import java.util.List;

public abstract class Aine {

    private List<Student> studentList;
    private int eap;
    private String name;

    public Aine(int EAP, String name) {
        this.eap = EAP;
        this.name = name;
        this.studentList = new LinkedList<>();
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
}
