class HallTicket {
    String studentName;
    int seatNumber;

    HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}

class hall {
    public static void main(String[] args) {

        HallTicket priya = new HallTicket("Priya", 0);

        // Both variables refer to the same object
        HallTicket copy = priya;

        copy.seatNumber = 45;

        // Separate object
        HallTicket separate = new HallTicket("Priya", 45);

        System.out.println(
                "Priya's seatNumber (via first variable): "
                        + priya.seatNumber
        );

        System.out.println("copy == priya: " + (copy == priya));
        System.out.println(
                "separate == priya: " + (separate == priya)
        );
    }
}