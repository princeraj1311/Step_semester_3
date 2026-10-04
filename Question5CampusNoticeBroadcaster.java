import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question5CampusNoticeBroadcaster {
    public static void main(String[] args) {
        Student asha = new Student("Asha", "CSE", new EmailChannel(), new AppChannel());
        Student ravi = new Student("Ravi", "ECE", new SmsChannel());

        NoticeBoard board = new NoticeBoard();
        board.addStudent(asha);
        board.addStudent(ravi);

        Notice labClosed = new Notice("Lab Closed Tomorrow", Set.of("CSE"));
        board.postNotice(labClosed);

        Notice feeDeadlineExtended = new Notice("Fee Deadline Extended", Set.of("CSE", "ECE"));
        board.postNotice(feeDeadlineExtended);

        Notice sportsDay = new Notice("Sports Day", Set.of());
        try {
            board.postNotice(sportsDay);
        } catch (IllegalArgumentException ex) {
            System.out.println("Cannot post notice: At least one target department is required.");
        }
    }
}

interface NotificationChannel {
    void send(String recipient, String message);
}

class EmailChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[Email → " + recipient + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[SMS → " + recipient + "] " + message);
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[App → " + recipient + "] " + message);
    }
}

class Student {
    private final String name;
    private final String department;
    private final List<NotificationChannel> preferredChannels;

    public Student(String name, String department, NotificationChannel... channels) {
        this.name = name;
        this.department = department;
        this.preferredChannels = new ArrayList<>(Arrays.asList(channels));
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }
}

class Notice {
    private final String title;
    private final Set<String> targetDepartments;

    public Notice(String title, Set<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = new HashSet<>(targetDepartments);
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTargetDepartments() {
        return new HashSet<>(targetDepartments);
    }

    public boolean isValid() {
        return title != null && !title.trim().isEmpty() && !targetDepartments.isEmpty();
    }
}

class NoticeBoard {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {
        if (!notice.isValid()) {
            throw new IllegalArgumentException("At least one target department is required.");
        }

        System.out.println("Notice '" + notice.getTitle() + "' posted to " + String.join(", ", notice.getTargetDepartments()) + ".");

        for (Student student : students) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student.getName(), notice.getTitle());
                }
            }
        }
    }
}
