package ee.taltech.iti0202.university.people;

import ee.taltech.iti0202.university.University;
import ee.taltech.iti0202.university.exception.CourseException;
import ee.taltech.iti0202.university.exception.StudentException;
import ee.taltech.iti0202.university.subject.Course;

import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;
import java.util.HashMap;

public class Student extends Person {

    private final List<Course> declaration;
    private final Map<Course, Integer> finishedGrade; //any finished courses with Grades, even 0
    private final Map<Course, Boolean> finishedCredits; //any finished courses with credit, (credit - arvestus)
    private Optional<University> studiedIn;
    private double averageScore;
    private Random random;
    private int sumOfEap;
    private static final float FORTY = 0.4f;
    private static final float QUATER = 0.25f;
    private static final float HALF = 0.5f;
    private static final int AGEOFMAJORITY = 18;

    enum ExamStrategy {
        RANDOM, SMART, STUPID, CHEAT
    }

    /**
     * Constructor.
     * getActiveCourses() - access to enrolled and not finished courses.
     * @param age
     * @param name
     */
    public Student(String name, int age) {
        super(name, age);
        this.studiedIn = Optional.empty();
        this.declaration = new LinkedList<>();
        this.finishedGrade = new HashMap<>();
        this.finishedCredits = new HashMap<>();
        this.sumOfEap = 0;
        this.random = new Random();
    }

    /**
     * Go study to university, if student is adult and currently not in a university.
     * @param university
     * @return
     */
    public boolean goToStudy(University university) {
        try {
            if (getAge() < AGEOFMAJORITY) {
                throw new StudentException(StudentException.Reason.UNDER_18);
            } else if (studiedIn.isPresent()) {
                throw new StudentException(StudentException.Reason.ALREADY_IN_UNI);
            }
            studiedIn = Optional.of(university);
            university.addStudent(this);
            return true;
        } catch (StudentException e) {
            System.out.println(getName() + e.getReason());
            return false;
        }
    }

    /**
     * Leave from university and delete all info except finished subjects.
     */
    public void leaveUniversity() {
        if (studiedIn.isPresent()) {
            studiedIn.get().getStudentList().remove(this);
            studiedIn = Optional.empty();
            for (Course course: getActiveCourses()) {
                course.getStudentList().remove(this);
            }
            getActiveCourses().clear();
            declaration.clear();
        }
    }

    /**
     * Add subject to declaration list, if subject is in university.
     * Student can declare subject even if he/she already failed it.
     * @param course
     * @return
     */
    public boolean addCourse(Course course) throws CourseException {
        try {
            if (!studiedIn.get().getCourseList().contains(course)) {
                throw new CourseException(CourseException.Reason.NOT_IN_UNI);
            }
            if (getDeclaration().contains(course)) {
                throw new CourseException(CourseException.Reason.ALREADY_IN_DECLARATION);
            }
            if (finishedGrade.containsKey(course)) {
                if (finishedGrade.get(course) != 0) {
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
            System.out.println(getName() + e.getReason());
            return false;
        }
    }

    /**
     * Add subjects to declaration list, if subject is in university.
     * Student can declare subject again if he/she failed it.
     * @param courses
     * @return
     */
    public void addCourse(List<Course> courses) throws CourseException {
        for (Course course : courses) {
            addCourse(course);
        }
    }

    /**
     * Declare all subjects of declaration, if there is no active subjects.
     * @return
     */
    public boolean declareSubjects() throws CourseException, StudentException {
        try {
            if (!getActiveCourses().isEmpty()) {
                throw new CourseException(CourseException.Reason.ACTIVE_SUBJECTS_IS_NOT_EMPTY);
            }
            for (Course course : declaration) {
                getActiveCourses().add(course);
                course.addStudent(this);
            }
            return true;
        } catch (CourseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Count average score (KKH) of Grade subjects.
     * @return
     */
    public double getAverageScore() {
        double sumOfEap = 0, gradesWithWeights = 0, averageScore = 0;
        for (Course course : finishedGrade.keySet()) {
            double aineEap = course.getEap();
            int grade = finishedGrade.get(course);
            if (grade == 0) {
                aineEap /= 2;
            }
            sumOfEap += aineEap;
            gradesWithWeights += grade * aineEap;
        }
        averageScore = gradesWithWeights / sumOfEap;
        averageScore = Math.ceil(averageScore * 10) / 10; //ümardamine
        return averageScore;
    }

    /**
     * Student get random number of points. Depends of that he gets Grade or credit(1-5 is pass; 0 is fail).
     * @param course
     * @return
     */
    public boolean getGrade(Course course, ExamStrategy examStrategy) {
        try {
            if (!getActiveCourses().contains(course)) {
                throw new CourseException(CourseException.Reason.NOT_IN_ACTIVE);
            }
            float procentCompletion;
            if (examStrategy.equals(ExamStrategy.RANDOM)) {
                procentCompletion = random.nextFloat();
            } else if (examStrategy.equals(ExamStrategy.STUPID)) {
                procentCompletion = random.nextFloat() * FORTY + FORTY;
            } else if (examStrategy.equals(ExamStrategy.SMART)) {
                procentCompletion = random.nextFloat() * QUATER + QUATER * 3;
            } else if (examStrategy.equals(ExamStrategy.CHEAT)) {
                procentCompletion = random.nextInt(1) * 1f;
            } else {
                procentCompletion = random.nextFloat() * HALF + HALF;
            }
            int grade = course.getTeacher().get().makeAssessment(procentCompletion);
            setGrade(course, grade);
            return true;
        } catch (CourseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Just get any grade or credit(1-5 is pass; 0 is fail).
     * @param course
     * @return
     */
    public boolean getGrade(Course course, int grade) {
        try {
            if (!getActiveCourses().contains(course)) {
                throw new CourseException(CourseException.Reason.NOT_IN_ACTIVE);
            } else if (grade > 5 && grade < 0) {
                throw new CourseException(CourseException.Reason.INVALID_GRADE);
            }
            setGrade(course, grade);
            return true;
        } catch (CourseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Set grade for course and finish it.
     * @param course
     * @param grade
     */
    public void setGrade(Course course, int grade) {
        if (course.isGrade()) {
            finishedGrade.put(course, grade);
        } else {
            if (grade == 0) {
                finishedCredits.put(course, false);
            } else {
                finishedCredits.put(course, true);
            }
        }
        getActiveCourses().remove(course);
        course.getStudentList().remove(this);
    }

    public List<Course> getDeclaration() {
        return declaration;
    }

    public Map<Course, Integer> getFinishedGrade() {
        return finishedGrade;
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
