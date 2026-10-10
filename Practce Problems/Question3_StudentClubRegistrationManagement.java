import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Question3_StudentClubRegistrationManagement {
    static class Student {
        private String rollNumber;
        private String name;

        Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Student)) {
                return false;
            }
            Student other = (Student) obj;
            return Objects.equals(rollNumber, other.rollNumber);
        }

        public int hashCode() {
            return Objects.hash(rollNumber);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<Student> members = new HashSet<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            String[] parts = trimmed.split("\\s+");
            String command = parts[0];

            if (command.equals("ADD")) {
                String rollNumber = parts[1];
                String name = parts[2];
                Student student = new Student(rollNumber, name);
                if (members.add(student)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (command.equals("CONTAINS")) {
                String rollNumber = parts[1];
                String name = parts[2];
                Student student = new Student(rollNumber, name);
                System.out.println("contains: " + members.contains(student));
            }
        }

        System.out.println("member count " + members.size());
    }
}
