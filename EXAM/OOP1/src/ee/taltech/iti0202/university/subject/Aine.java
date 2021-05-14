package ee.taltech.iti0202.university.subject;

import ee.taltech.iti0202.university.exception.StudentException;
import ee.taltech.iti0202.university.student.Student;

import java.util.LinkedList;
import java.util.List;

public class Aine {

    private final List<Student> studentList;
    private int eap;
    private final String name;
    private AineTüüb aineTüüb;
    private final boolean isAssessment;

    /**
     * Type of subject.
     */
    public enum AineTüüb {
        VABAÕPE, ERIALA, ÜLDÕPE
    }

    /**
     * Abstract constructor.
     * @param eap number of eap.
     * @param name of subject.
     */
    public Aine(String name, int eap, boolean isAssessment, AineTüüb aineTüüb) {
        this.eap = eap;
        this.name = name;
        this.studentList = new LinkedList<>();
        this.isAssessment = isAssessment;
        this.aineTüüb = aineTüüb;
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

    public AineTüüb getAineTüüb() {
        return aineTüüb;
    }

    public boolean isAssessment() {
        return isAssessment;
    }
}
