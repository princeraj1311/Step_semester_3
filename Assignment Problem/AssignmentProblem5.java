import java.util.Arrays;

public class AssignmentProblem5 {

    public static class LoanReceipt {
        private final String memberId;
        private final String[] bookIds;

        public LoanReceipt(String memberId, String[] bookIds) {
            this.memberId = memberId;
            this.bookIds = (bookIds != null) ? bookIds.clone() : new String[0];
        }

        public String getMemberId() {
            return memberId;
        }

        public String[] getBookIds() {
            return bookIds.clone();
        }

        public LoanReceipt withCorrectedBookId(int index, String newId) {
            if (index < 0 || index >= this.bookIds.length) {
                return this;
            }
            String[] updatedBookIds = this.bookIds.clone();
            updatedBookIds[index] = newId;
            return new LoanReceipt(this.memberId, updatedBookIds);
        }
    }

    public static class ReferenceOnlyLoanReceipt extends LoanReceipt {
        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    public static class CirculationLedger {
        private static String branchCode;

        static {
            branchCode = "PAGE-TURNER-MAIN";
        }

        public static String processNightlyCirculation(LoanReceipt[] receipts) {
            int totalProcessed = 0;
            int nullCount = 0;
            int referenceOnlyCount = 0;
            int regularCount = 0;

            if (receipts != null) {
                for (LoanReceipt receipt : receipts) {
                    if (receipt == null) {
                        nullCount++;
                        continue;
                    }

                    totalProcessed++;
                    if (receipt instanceof ReferenceOnlyLoanReceipt) {
                        referenceOnlyCount++;
                    } else {
                        regularCount++;
                    }
                }
            }

            return totalProcessed + " processed | "
                    + nullCount + " null skipped | "
                    + referenceOnlyCount + " reference-only | "
                    + regularCount + " regular";
        }
    }

    public static void main(String[] args) {
        // Defensive copying verification
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]); // Outputs BK-100

        // Wither pattern verification
        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println(Arrays.toString(r.getBookIds()));         // Outputs [BK-100, BK-101]
        System.out.println(Arrays.toString(corrected.getBookIds())); // Outputs [BK-100, BK-102]

        // Ledger processing verification
        LoanReceipt[] nightlyBatch = new LoanReceipt[]{
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
                null,
                new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };

        System.out.println(CirculationLedger.processNightlyCirculation(nightlyBatch));
    }
}
