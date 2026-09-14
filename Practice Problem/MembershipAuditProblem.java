class LibraryMember5 {

    private static int memberCounter = 100;

    protected int borrowLimit;
    protected int booksBorrowed;

    public final String memberNumber;

    public LibraryMember5(int borrowLimit) {

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException(
                    "Borrow limit must be positive"
            );
        }

        memberCounter++;

        memberNumber =
                "LIB-" + memberCounter;

        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {

        System.out.println(
                "Genre: " + genre
        );

        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static boolean isValidRenewalCode(
            String code
    ) {

        if (code == null ||
                code.length() != 4) {

            return false;
        }

        if (code.charAt(0) != 'R') {
            return false;
        }

        if (!Character.isDigit(
                code.charAt(1))) {

            return false;
        }

        if (!Character.isDigit(
                code.charAt(2))) {

            return false;
        }

        if (!Character.isUpperCase(
                code.charAt(3))) {

            return false;
        }

        return true;
    }

    public static int getMembersEnrolled() {

        return memberCounter - 100;
    }
}

class FacultyMember5 extends LibraryMember5 {

    private String department;

    public FacultyMember5(
            int borrowLimit,
            String department
    ) {
        super(borrowLimit);
        this.department = department;
    }
}

public class MembershipAuditProblem {

    static String processNightlyAudit(
            LibraryMember5[] members
    ) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember5 member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember5) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + faculty
                + " faculty | "
                + regular
                + " regular";
    }

    public static void main(String[] args) {

        LibraryMember5 m1 =
                new LibraryMember5(3);

        System.out.println(
                m1.memberNumber
        );

        System.out.println(
                LibraryMember5.getMembersEnrolled()
        );

        System.out.println(
                LibraryMember5.isValidRenewalCode(
                        "R12A"
                )
        );

        System.out.println(
                LibraryMember5.isValidRenewalCode(
                        "R1A"
                )
        );

        System.out.println(
                LibraryMember5.isValidRenewalCode(
                        "X12A"
                )
        );

        m1.borrowBook();

        m1.borrowBook("Fiction");

        System.out.println(
                m1.getBooksBorrowed()
        );

        LibraryMember5[] members = {

                new FacultyMember5(
                        5, "Physics"
                ),

                null,

                new LibraryMember5(3)
        };

        System.out.println(
                processNightlyAudit(members)
        );
    }
}