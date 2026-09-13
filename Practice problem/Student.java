class Student {
    // Instance fields
    String name;
    double attendance;

    // Static fields shared across all instances
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    // Constructor
    public Student(String name, double attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++; // Increment static counter
    }

    // Static method accessing only static members
    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public static void main(String[] args) {
        Student s1 = new Student("Ravi", 85.0);
        Student s2 = new Student("Anitha", 92.5);

        // Call static method directly via the Class Name
        Student.printCollegeInfo();
    }
}
