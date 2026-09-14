import java.util.Arrays;

class LibraryMember3 {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    private int[] fineHistory;
    private int fineCount;

    public LibraryMember3(
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

        fineHistory = new int[10];
        fineCount = 0;
    }

    protected void chargeFine(int amount) {

        if (fineCount < fineHistory.length) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }
    }

    public int[] getFineHistory() {

        return Arrays.copyOf(
                fineHistory,
                fineCount
        );
    }

    public int getTotalFine() {

        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }
}

class StudentMember3 extends LibraryMember3 {

    private String course;

    public StudentMember3(
            String memberId,
            int borrowLimit,
            String course
    ) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {

        super.chargeFine(amount / 2);
    }
}

public class FineLedgerProblem {

    public static void main(String[] args) {

        StudentMember3 s =
                new StudentMember3(
                        "STU5", 3, "CSE"
                );

        s.chargeFine(100);

        System.out.println(
                s.getTotalFine()
        );

        int[] history =
                s.getFineHistory();

        history[0] = 999;

        System.out.println(
                Arrays.toString(
                        s.getFineHistory()
                )
        );
    }
}
