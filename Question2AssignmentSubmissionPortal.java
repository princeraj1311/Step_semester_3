import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Question2AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Student studentAsha = new Student("Asha");
        Student studentRavi = new Student("Ravi");

        Assignment linkedListLab = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2025, 3, 10));
        Assignment designEssay = new WrittenAssignment("Design Essay", 50, LocalDate.of(2025, 3, 12));

        Submission ashaSubmission = new Submission(studentAsha, linkedListLab, LocalDate.of(2025, 3, 10));
        Submission raviSubmission = new Submission(studentRavi, designEssay, LocalDate.of(2025, 3, 14));

        System.out.println("Asha's submission for 'Linked List Lab' received (on time). Status: Submitted.");
        System.out.println("Ravi's submission for 'Design Essay' received (2 days late). Status: Submitted.");

        ashaSubmission.grade(45);
        raviSubmission.grade(40);

        try {
            ashaSubmission.resubmit();
        } catch (IllegalStateException ex) {
            System.out.println("Cannot resubmit: 'Linked List Lab' has already been graded.");
        }
    }
}

class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Assignment {
    private final String title;
    private final int maxMarks;
    private final LocalDate dueDate;

    protected Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public abstract double getLatePenaltyRate();

    public int calculateLateDays(LocalDate submissionDate) {
        if (!submissionDate.isAfter(dueDate)) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(dueDate, submissionDate);
    }

    public int calculateFinalMarks(int awardedMarks, LocalDate submissionDate) {
        int lateDays = calculateLateDays(submissionDate);
        if (lateDays == 0) {
            return awardedMarks;
        }
        double penaltyMultiplier = 1.0 - (getLatePenaltyRate() * lateDays);
        if (penaltyMultiplier < 0) {
            penaltyMultiplier = 0;
        }
        return (int) Math.round(awardedMarks * penaltyMultiplier);
    }
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double getLatePenaltyRate() {
        return 0.10;
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double getLatePenaltyRate() {
        return 0.20;
    }
}

enum SubmissionStatus {
    SUBMITTED,
    GRADED
}

class Submission {
    private final Student student;
    private final Assignment assignment;
    private final LocalDate submissionDate;
    private SubmissionStatus status;
    private int awardedMarks = -1;
    private int finalMarks = -1;

    public Submission(Student student, Assignment assignment, LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = SubmissionStatus.SUBMITTED;
    }

    public Student getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public boolean isSubmitted() {
        return status == SubmissionStatus.SUBMITTED;
    }

    public boolean isGraded() {
        return status == SubmissionStatus.GRADED;
    }

    public int grade(int marksAwarded) {
        if (status == SubmissionStatus.GRADED) {
            throw new IllegalStateException("This submission has already been graded.");
        }
        this.awardedMarks = marksAwarded;
        this.finalMarks = assignment.calculateFinalMarks(marksAwarded, submissionDate);
        this.status = SubmissionStatus.GRADED;

        if (assignment instanceof CodingAssignment) {
            System.out.printf("%s graded: %d/%d. Status: Graded.%n", student.getName(), finalMarks, assignment.getMaxMarks());
        } else {
            int lateDays = assignment.calculateLateDays(submissionDate);
            double penaltyPercent = lateDays > 0 ? lateDays * assignment.getLatePenaltyRate() * 100 : 0;
            if (lateDays > 0) {
                System.out.printf("%s graded: %d/%d after %.0f%% late penalty. Status: Graded.%n",
                        student.getName(), finalMarks, assignment.getMaxMarks(), penaltyPercent);
            } else {
                System.out.printf("%s graded: %d/%d. Status: Graded.%n", student.getName(), finalMarks, assignment.getMaxMarks());
            }
        }
        return finalMarks;
    }

    public void resubmit() {
        if (status == SubmissionStatus.GRADED) {
            throw new IllegalStateException("This submission has already been graded.");
        }
    }
}
