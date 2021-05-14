package ee.taltech.iti0202.university.student;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exception.CourseException;
import ee.taltech.iti0202.university.exception.StudentException;
import ee.taltech.iti0202.university.subject.Aine;

import java.util.Optional;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;

public class Student {

    private final String name;
    private int age;
    private final List<Aine> activeCourses; // currently enrolled courses
    private final List<Aine> declaration;
    private final Map<Aine, Integer> finishedAssessment; //any finished courses with assessments, even 0
    private final Map<Aine, Boolean> finishedCredits; //any finished courses with credit, (credit - arvestus)
    private Optional<University> studiedIn;
    private double averageScore;
    private int sumOfEap;
    static private final int AgeOfMajority = 18;

    /**
     * Constructor.
     * @param age
     * @param name
     */
    public Student(int age, String name) {
        this.age = age;
        this.name = name;
        this.studiedIn = Optional.empty();
        this.activeCourses = new LinkedList<>();
        this.declaration = new LinkedList<>();
        this.finishedAssessment = new HashMap<>();
        this.finishedCredits = new HashMap<>();
        this.averageScore = 0;
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
            if (age <= AgeOfMajority) {
                throw new StudentException(StudentException.Reason.UNDER_18);
            } else if (studiedIn.isPresent()) {
                throw new StudentException(StudentException.Reason.ALREADY_IN_UNI) ;
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
     * @param aine
     * @return
     */
    public boolean declareAine(Aine aine) {
        try {
            if (!studiedIn.get().getAineList().contains(aine)) {
                throw new CourseException(CourseException.Reason.NOT_IN_UNI);
            }
            if (getDeclaration().contains(aine)) {
                throw new CourseException(CourseException.Reason.NOT_IN_UNI);
            }
            if (finishedAssessment.containsKey(aine)) {
                if (finishedAssessment.get(aine) != 0) {
                    throw new CourseException(CourseException.Reason.COURSE_IS_PASSED);
                }
            } else if (finishedCredits.containsKey(aine)) {
                if (finishedCredits.get(aine)) {
                    throw new CourseException(CourseException.Reason.COURSE_IS_PASSED);
                }
            }
            declaration.add(aine);
            return true;
        } catch (CourseException e) {
            e.printStackTrace();
            System.out.println(name + e.getReason());
            return false;
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
            for (Aine aine: declaration) {
                activeCourses.add(aine);
                aine.addStudent(this);
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
        for (Aine aine: finishedAssessment.keySet()) {
            double aineEap = aine.getEap();
            int assessment = finishedAssessment.get(aine);
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

    public List<Aine> getActiveCourses() {
        return activeCourses;
    }

    public List<Aine> getDeclaration() {
        return declaration;
    }

    public Map<Aine, Integer> getFinishedAssessment() {
        return finishedAssessment;
    }

    public Map<Aine, Boolean> getFinishedCredits() {
        return finishedCredits;
    }

    public int getSumOfEap() {
        return sumOfEap;
    }
}
