package ee.taltech.iti0202.university.people;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exception.CourseException;
import ee.taltech.iti0202.university.exception.StudentException;
import ee.taltech.iti0202.university.subject.Course;

import java.util.*;

public class Student {

    private final String name;
    private int age;
    private final List<Course> activeCourses; // currently enrolled courses
    private final List<Course> declaration;
    private final Map<Course, Integer> finishedAssessment; //any finished courses with assessments, even 0
    private final Map<Course, Boolean> finishedCredits; //any finished courses with credit, (credit - arvestus)
    private Optional<University> studiedIn;
    private double averageScore;
    private int sumOfEap;
    private static final int AGEOFMAJORITY = 18;

    /**
     * Constructor.
     * @param age
     * @param name
     */
    public Student(String name, int age) {
        this.age = age;
        this.name = name;
        this.studiedIn = Optional.empty();
        this.activeCourses = new LinkedList<>();
        this.declaration = new LinkedList<>();
        this.finishedAssessment = new HashMap<>();
        this.finishedCredits = new HashMap<>();
        this.sumOfEap = 0;
    }

    /**
     * Go study to university, if student is adult and currently not in a university.
     * @param university
     * @return
     * @throws StudentException
     */
    public boolean goToStudy(University university) throws StudentException {
        try {
            if (age < AGEOFMAJORITY) {
                throw new StudentException(StudentException.Reason.UNDER_18);
            } else if (studiedIn.isPresent()) {
                throw new StudentException(StudentException.Reason.ALREADY_IN_UNI);
            }
            studiedIn = Optional.of(university);
            university.addStudent(this);
            return true;
        } catch (StudentException e) {
            System.out.println(name + e.getReason());
            return false;
        }
    }

    /**
     * Add subject to declaration list, if subject is in university.
     * Student can declare subject even if he/she already failed it.
     * @param course
     * @return
     */
    public boolean declareCourse(Course course) {
        try {
            if (!studiedIn.get().getCourseList().contains(course)) {
                throw new CourseException(CourseException.Reason.NOT_IN_UNI);
            }
            if (getDeclaration().contains(course)) {
                throw new CourseException(CourseException.Reason.NOT_IN_UNI);
            }
            if (finishedAssessment.containsKey(course)) {
                if (finishedAssessment.get(course) != 0) {
                    throw new CourseException(CourseException.Reason.COURSE_IS_PASSED);
                }
            } else if (finishedCredits.containsKey(course)) {
                if (finishedCredits.get(course)) {
                    throw new CourseException(CourseException.Reason.COURSE_IS_PASSED);
                }
            }
            declaration.add(course);
            return true;
        } catch (CourseException e) {
            e.printStackTrace();
            System.out.println(name + e.getReason());
            return false;
        }
    }

    public void declareCourse(List<Course> courses) {
        for (Course course : courses) {
            declareCourse(course);
        }
    }

    /**
     * Declare all subjects of declaration, if there is no active subjects.
     * @return
     */
    public boolean declareSubjects() {
        try {
            if (!activeCourses.isEmpty()) {
                throw new CourseException(CourseException.Reason.ACTIVE_SUBJECTS_IS_NOT_EMPTY);
            }
            for (Course course : declaration) {
                activeCourses.add(course);
                course.addStudent(this);
            }
            return true;
        } catch (CourseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Count average score (KKH) of assessment subjects.
     * @return
     */
    public double getAverageScore() {
        double sumOfEap = 0, assessmentsWithWeights = 0, averageScore = 0;
        for (Course course : finishedAssessment.keySet()) {
            double aineEap = course.getEap();
            int assessment = finishedAssessment.get(course);
            if (assessment == 0) {
                aineEap /= 2;
            }
            sumOfEap += aineEap;
            assessmentsWithWeights += assessment * aineEap;
        }
        averageScore = assessmentsWithWeights / sumOfEap;
        averageScore = Math.ceil(averageScore * 10) / 10; //ümardamine
        return averageScore;
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

    public List<Course> getDeclaration() {
        return declaration;
    }

    public Map<Course, Integer> getFinishedAssessment() {
        return finishedAssessment;
    }

    public Map<Course, Boolean> getFinishedCredits() {
        return finishedCredits;
    }

    public int getSumOfEap() {
        return sumOfEap;
    }

    public Optional<University> getStudiedIn() {
        return studiedIn;
    }
}
