public class CSVStudentParser {

    public static void parseStudentRecord(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        // Split by comma
        String[] fields = csvLine.split(",");

        // Validate exactly 3 fields
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        // Trim each field to remove extra spaces
        String name = fields[0].trim();
        String rollNumber = fields[1].trim();
        String department = fields[2].trim();

        // Validate fields are not empty
        if (name.isEmpty() || rollNumber.isEmpty() || department.isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.printf("Name: %s | Roll No: %s | Dept: %s%n", name, rollNumber, department);
    }

    public static void main(String[] args) {
        // Test cases
        parseStudentRecord("Ananya Verma,RA2211003010123,CSE");
        parseStudentRecord("John Doe,RA2211003010456,ECE");
        parseStudentRecord("Invalid,Record");
        parseStudentRecord("");
    }
}