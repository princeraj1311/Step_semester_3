class LibraryMember2 {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember2(String memberId, int borrowLimit) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Invalid borrow limit");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println(
                "General Member | Books Borrowed: "
                        + booksBorrowed
        );
    }
}

class StudentMember2 extends LibraryMember2 {

    protected String course;

    public StudentMember2(
            String memberId,
            int borrowLimit,
            String course
    ) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Student Member | Course: "
                        + course
                        + " | Books Borrowed: "
                        + booksBorrowed
        );
    }
}

class HonorsStudentMember extends StudentMember2 {

    private int bonusLimit;

    public HonorsStudentMember(
            String memberId,
            int borrowLimit,
            String course,
            int bonusLimit
    ) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Honors Student Member | Course: "
                        + course
                        + " | Bonus Limit: "
                        + bonusLimit
                        + " | Books Borrowed: "
                        + booksBorrowed
        );
    }
}

class FacultyMember2 extends LibraryMember2 {

    private String department;

    public FacultyMember2(
            String memberId,
            int borrowLimit,
            String department
    ) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Faculty Member | Department: "
                        + department
                        + " | Books Borrowed: "
                        + booksBorrowed
        );
    }
}

public class MembershipTreeProblem {

    static String classifyGeneration(LibraryMember2 member) {

        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof FacultyMember2) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof StudentMember2) {
            return "Student branch";
        }

        return "General Member";
    }

    static int getTotalBooksBorrowed(
            LibraryMember2[] members
    ) {

        int total = 0;

        for (LibraryMember2 member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }

    public static void main(String[] args) {

        LibraryMember2 general =
                new LibraryMember2("STU1", 3);

        StudentMember2 student =
                new StudentMember2("STU2", 3, "CSE");

        HonorsStudentMember honors =
                new HonorsStudentMember(
                        "STU3", 3, "ECE", 2
                );

        FacultyMember2 faculty =
                new FacultyMember2(
                        "STU4", 5, "Physics"
                );

        general.displayInfo();
        student.displayInfo();
        honors.displayInfo();
        faculty.displayInfo();

        System.out.println(
                classifyGeneration(honors)
        );

        System.out.println(
                classifyGeneration(faculty)
        );

        student.borrowBook();
        student.borrowBook();

        honors.borrowBook();

        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        LibraryMember2[] members = {
                student, honors, faculty
        };

        System.out.println(
                getTotalBooksBorrowed(members)
        );
    }
}