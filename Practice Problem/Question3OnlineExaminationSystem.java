import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question3OnlineExaminationSystem {
    public static void main(String[] args) {
        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");
        exam.addQuestion(new MultipleChoiceQuestion("Q1", "What is 2 + 2?", "4", 5));
        exam.addQuestion(new TrueFalseQuestion("Q2", "Java is an object-oriented language.", true, 5));

        Attempt attempt = student.startExam(exam);
        attempt.answerQuestion("Q1", "C");
        attempt.answerQuestion("Q2", "True");
        attempt.submit();

        System.out.println("Result: Q1 - " + attempt.getResultForQuestion("Q1") + ", Q2 - " + attempt.getResultForQuestion("Q2"));
        System.out.println("Total score: " + attempt.getTotalScore() + "/10");

        attempt.answerQuestion("Q1", "D");
    }
}

enum AttemptStatus {
    IN_PROGRESS,
    SUBMITTED
}

class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Attempt startExam(Examination examination) {
        return new Attempt(this, examination);
    }
}

abstract class Question {
    private final String questionId;
    private final String text;
    private final int maxMarks;

    public Question(String questionId, String text, int maxMarks) {
        this.questionId = questionId;
        this.text = text;
        this.maxMarks = maxMarks;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getText() {
        return text;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public abstract boolean isCorrect(String answer);
}

class MultipleChoiceQuestion extends Question {
    private final String correctOption;

    public MultipleChoiceQuestion(String questionId, String text, String correctOption, int maxMarks) {
        super(questionId, text, maxMarks);
        this.correctOption = correctOption;
    }

    @Override
    public boolean isCorrect(String answer) {
        return correctOption.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    private final boolean correctAnswer;

    public TrueFalseQuestion(String questionId, String text, boolean correctAnswer, int maxMarks) {
        super(questionId, text, maxMarks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean isCorrect(String answer) {
        return answer != null && Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class ShortAnswerQuestion extends Question {
    private final String correctAnswer;

    public ShortAnswerQuestion(String questionId, String text, String correctAnswer, int maxMarks) {
        super(questionId, text, maxMarks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean isCorrect(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    private final String title;
    private final List<Question> questions = new ArrayList<>();

    public Examination(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getQuestion(String questionId) {
        for (Question question : questions) {
            if (question.getQuestionId().equals(questionId)) {
                return question;
            }
        }
        return null;
    }
}

class Attempt {
    private final Student student;
    private final Examination examination;
    private final Map<String, String> answers = new HashMap<>();
    private AttemptStatus status = AttemptStatus.IN_PROGRESS;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    public Student getStudent() {
        return student;
    }

    public Examination getExamination() {
        return examination;
    }

    public void answerQuestion(String questionId, String answer) {
        if (status == AttemptStatus.SUBMITTED) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        answers.put(questionId, answer);
        System.out.println("Answer recorded for " + questionId + ".");
    }

    public void submit() {
        if (status == AttemptStatus.SUBMITTED) {
            System.out.println("This attempt has already been submitted.");
            return;
        }
        status = AttemptStatus.SUBMITTED;
        System.out.println(examination.getTitle() + " submitted by " + student.getName() + ".");
    }

    public String getResultForQuestion(String questionId) {
        Question question = examination.getQuestion(questionId);
        String answer = answers.get(questionId);
        if (question == null) {
            return "Not found";
        }

        if (status != AttemptStatus.SUBMITTED) {
            return "Pending";
        }

        if (question.isCorrect(answer)) {
            return "Correct (" + question.getMaxMarks() + " points)";
        }
        return "Incorrect (0 points)";
    }

    public int getTotalScore() {
        int total = 0;
        for (Question question : examination.getQuestions()) {
            if (question.isCorrect(answers.get(question.getQuestionId()))) {
                total += question.getMaxMarks();
            }
        }
        return total;
    }
}
