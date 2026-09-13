class Course {
    String code;
    String title;
    int credits;
    int labCredits;

    // Full 4-argument constructor
    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    // Overloaded 3-argument constructor chaining via this(...)
    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    // Method to calculate total credits
    public int totalCredits() {
        return this.credits + this.labCredits;
    }

    public static void main(String[] args) {
        Course course1 = new Course("21CSC201J", "Data Structures", 4);
        Course course2 = new Course("21CSC205L", "DSA Lab", 3, 1);

        System.out.println(course1.code + " total credits: " + course1.totalCredits());
        System.out.println(course2.code + " total credits: " + course2.totalCredits());
    }
}