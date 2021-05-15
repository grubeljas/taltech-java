package ee.taltech.iti0202.university.people;

import java.util.LinkedList;
import java.util.List;

public class Teacher extends Person{

    private Float[] systemOfAssessment = new Float[5];
    private final Float[] DEFAULT_ASSESSMENT = new Float[]{0.5f, 0.6f, 0.7f, 0.8f, 0.9f};

    /**
     * Teacher's constructor. Attached to university.
     * Teacher can't quit the job or change uni.
     * Has system of assessment (arrange of 5 floats),
     * each float is minimum procent of each positive grade (from 1 to 5).
     * getActiveCourses() - access to courses teacher controls.
     * @param name
     * @param age
     * @param systemOfAssessment
     */
    public Teacher(String name, int age, Float[] systemOfAssessment) {
        super(name, age);
        this.systemOfAssessment = systemOfAssessment;
    }

    /**
     * Teacher's constructor. Attached to university.
     * Teacher can't quit the job or change uni.
     * Default system of assessment.
     * getActiveCourses() - access to courses teacher controls.
     * @param name
     * @param age
     */
    public Teacher(String name, int age) {
        super(name, age);
        this.systemOfAssessment = DEFAULT_ASSESSMENT;
    }

    public int makeAssessment(float procentOfCmpletedWork) {
        int grade;
        if (procentOfCmpletedWork > systemOfAssessment[4]) {
            grade = 5;
        } else if (procentOfCmpletedWork > systemOfAssessment[3]) {
            grade = 4;
        } else if (procentOfCmpletedWork > systemOfAssessment[2]) {
            grade = 3;
        } else if (procentOfCmpletedWork > systemOfAssessment[1]) {
            grade = 2;
        } else if (procentOfCmpletedWork > systemOfAssessment[0]) {
            grade = 1;
        } else {
            grade = 0;
        }
        return grade;
    }

    public void setSystemOfAssessment(Float[] systemOfAssessment) {
        this.systemOfAssessment = systemOfAssessment;
    }
}
