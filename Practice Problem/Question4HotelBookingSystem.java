import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Question4HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Room standard101 = new StandardRoom("101");
        Room deluxe201 = new DeluxeRoom("201");
        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        hotel.addRoom(standard101);
        hotel.addRoom(deluxe201);

        LocalDate start1 = LocalDate.of(2026, 1, 1);
        LocalDate end1 = LocalDate.of(2026, 1, 5);
        System.out.println("Standard Room 101 is available from Jan 1 to Jan 5: " + hotel.isAvailable(standard101, start1, end1));

        Reservation reservationA = hotel.createReservation(customerA, standard101, start1, end1);
        System.out.println("Reservation confirmed for " + customerA.getName() + ", Standard Room 101 (Jan 1-5). Price: $" + reservationA.getTotalPrice());

        LocalDate start2 = LocalDate.of(2026, 1, 3);
        LocalDate end2 = LocalDate.of(2026, 1, 7);
        System.out.println("Standard Room 101 is available from Jan 3 to Jan 7: " + hotel.isAvailable(standard101, start2, end2));

        Reservation reservationB = hotel.createReservation(customerB, standard101, start2, end2);
        if (reservationB == null) {
            System.out.println("Standard Room 101 is not available from Jan 3 to Jan 7.");
        }

        hotel.cancelReservation(reservationA);
        System.out.println("Reservation for Customer A, Standard Room 101 (Jan 1-5) cancelled successfully.");

        LocalDate start3 = LocalDate.of(2026, 2, 10);
        LocalDate end3 = LocalDate.of(2026, 2, 12);
        Reservation reservationC = hotel.createReservation(customerC, deluxe201, start3, end3);
        System.out.println("Reservation confirmed for " + customerC.getName() + ", Deluxe Room 201 (Feb 10-12). Price: $" + reservationC.getTotalPrice());
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

abstract class Room {
    private final String roomNumber;
    private final String roomType;

    public Room(String roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public abstract double calculatePrice(int nights);
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber) {
        super(roomNumber, "Standard");
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * 120.0;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) {
        super(roomNumber, "Deluxe");
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * 180.0;
    }
}

class Suite extends Room {
    public Suite(String roomNumber) {
        super(roomNumber, "Suite");
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * 260.0;
    }
}

class Reservation {
    private final Customer customer;
    private final Room room;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;
    private boolean cancelled;

    public Reservation(Customer customer, Room room, LocalDate startDate, LocalDate endDate) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = room.calculatePrice((int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate));
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        cancelled = true;
    }
}

class Hotel {
    private final List<Room> rooms = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean isAvailable(Room room, LocalDate startDate, LocalDate endDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room) && !reservation.isCancelled()) {
                boolean overlaps = !(endDate.isBefore(reservation.getStartDate()) || startDate.isAfter(reservation.getEndDate()));
                if (overlaps) {
                    return false;
                }
            }
        }
        return true;
    }

    public Reservation createReservation(Customer customer, Room room, LocalDate startDate, LocalDate endDate) {
        if (!isAvailable(room, startDate, endDate)) {
            return null;
        }

        Reservation reservation = new Reservation(customer, room, startDate, endDate);
        reservations.add(reservation);
        return reservation;
    }

    public void cancelReservation(Reservation reservation) {
        if (reservation == null) {
            return;
        }

        reservation.cancel();
    }
}
