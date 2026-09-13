public class AssignmentProblem3 {

    public static class BookInventory {
        private int copiesTotal;
        private int copiesAvailable;

        public BookInventory(int copiesTotal) {
            this.copiesTotal = Math.max(0, copiesTotal);
            this.copiesAvailable = this.copiesTotal;
        }

        public void checkOut() {
            if (copiesAvailable > 0) {
                copiesAvailable--;
            }
        }

        public void checkin() {
            if (copiesAvailable < copiesTotal) {
                copiesAvailable++;
            }
        }

        public int getCopiesAvailable() {
            return copiesAvailable;
        }
    }

    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut(); // 4th attempt - silently rejected
        System.out.println("Available copies: " + b.getCopiesAvailable());

        b.checkin();
        b.checkin();
        b.checkin();
        b.checkin(); // 4th attempt - silently rejected
        System.out.println("Available copies: " + b.getCopiesAvailable());
    }
}