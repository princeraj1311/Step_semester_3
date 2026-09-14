class LibraryMember4 {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember4(
            String memberId,
            int borrowLimit
    ) {

        if (memberId == null ||
                memberId.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Invalid member ID"
            );
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException(
                    "Invalid borrow limit"
            );
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

        System.out.print(
                "General | Books: "
                        + booksBorrowed
        );
    }
}

class StudentMember4 extends LibraryMember4 {

    private String course;

    public StudentMember4(
            String memberId,
            int borrowLimit,
            String course
    ) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public void displayInfo() {

        System.out.print(
                "Student | Course: "
                        + course
                        + " | Books: "
                        + booksBorrowed
        );
    }
}

public class CirculationReportProblem {

    static String batchPrint(
            LibraryMember4[] members
    ) {

        StringBuilder result =
                new StringBuilder();

        for (LibraryMember4 member : members) {

            member.displayInfo();

            if (member instanceof StudentMember4) {

                StudentMember4 student =
                        (StudentMember4) member;

                result.append(
                        "Student | Course: "
                                + student.getCourse()
                                + " | Books: "
                                + student.getBooksBorrowed()
                                + " [Course via downcast: "
                                + student.getCourse()
                                + "] | "
                );

            } else {

                result.append(
                        "General | Books: "
                                + member.getBooksBorrowed()
                                + " | "
                );
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        LibraryMember4[] members = {

                new LibraryMember4("LB5", 3),

                new StudentMember4(
                        "STU6", 3, "ECE"
                )
        };

        System.out.println(
                batchPrint(members)
        );
    }
}