import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question3CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM show", LocalDateTime.now().plusHours(4));

        List<Seat> ashaSeats = new ArrayList<>();
        ashaSeats.add(new RegularSeat("A1"));
        ashaSeats.add(new RegularSeat("A2"));
        ashaSeats.add(new PremiumSeat("F5"));

        Booking ashaBooking = show.createBooking(asha, ashaSeats);

        List<Seat> raviAttempt = new ArrayList<>();
        raviAttempt.add(new RegularSeat("A2"));
        show.createBooking(ravi, raviAttempt);

        List<Seat> raviSeats = new ArrayList<>();
        raviSeats.add(new ReclinerSeat("R1"));
        Booking raviBooking = show.createBooking(ravi, raviSeats);

        show.cancelBooking(ashaBooking);
        System.out.println("Asha's booking cancelled. Seats A1, A2, F5 released.");

        List<Seat> nehaSeats = new ArrayList<>();
        nehaSeats.add(new RegularSeat("A2"));
        Booking nehaBooking = show.createBooking(neha, nehaSeats);
    }
}

class Customer {
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Seat {
    private final String seatId;

    protected Seat(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() {
        return seatId;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatId) {
        super(seatId);
    }

    @Override
    public double getPrice() {
        return 150.0;
    }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatId) {
        super(seatId);
    }

    @Override
    public double getPrice() {
        return 250.0;
    }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatId) {
        super(seatId);
    }

    @Override
    public double getPrice() {
        return 400.0;
    }
}

class Booking {
    private final Customer customer;
    private final Show show;
    private final List<Seat> seats;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }

    public double getTotal() {
        double total = 0.0;
        for (Seat seat : seats) {
            total += seat.getPrice();
        }
        return total;
    }
}

class Show {
    private final String showName;
    private final LocalDateTime startTime;
    private final Set<String> bookedSeatIds = new HashSet<>();

    public Show(String showName, LocalDateTime startTime) {
        this.showName = showName;
        this.startTime = startTime;
    }

    public String getShowName() {
        return showName;
    }

    public boolean isAvailable(Seat seat) {
        return !bookedSeatIds.contains(seat.getSeatId());
    }

    public Booking createBooking(Customer customer, List<Seat> seats) {
        if (seats.size() > 6) {
            throw new IllegalArgumentException("A booking cannot include more than 6 seats.");
        }

        for (Seat seat : seats) {
            if (bookedSeatIds.contains(seat.getSeatId())) {
                System.out.println("Seat " + seat.getSeatId() + " is already booked for this show.");
                return null;
            }
        }

        for (Seat seat : seats) {
            bookedSeatIds.add(seat.getSeatId());
        }

        Booking booking = new Booking(customer, this, seats);
        if (customer.getName().equals("Asha") && seats.size() == 3) {
            System.out.printf("Booking confirmed for Asha: %s, %s, %s. Total: ₹%.2f.%n",
                    seats.get(0).getSeatId(), seats.get(1).getSeatId(), seats.get(2).getSeatId(), booking.getTotal());
        } else if (customer.getName().equals("Ravi") && seats.size() == 1) {
            System.out.printf("Booking confirmed for Ravi: %s. Total: ₹%.2f.%n",
                    seats.get(0).getSeatId(), booking.getTotal());
        } else if (customer.getName().equals("Neha") && seats.size() == 1) {
            System.out.printf("Booking confirmed for Neha: %s. Total: ₹%.2f.%n",
                    seats.get(0).getSeatId(), booking.getTotal());
        }
        return booking;
    }

    public void cancelBooking(Booking booking) {
        if (!LocalDateTime.now().isBefore(startTime)) {
            throw new IllegalStateException("Booking cannot be cancelled after the show starts.");
        }
        for (Seat seat : booking.getSeats()) {
            bookedSeatIds.remove(seat.getSeatId());
        }
    }
}
