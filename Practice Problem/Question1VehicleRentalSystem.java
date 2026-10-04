import java.util.ArrayList;
import java.util.List;

public class Question1VehicleRentalSystem {
    public static void main(String[] args) {
        VehicleRentalSystem rentalSystem = new VehicleRentalSystem();

        Customer alice = new Customer("Alice");
        Customer bob = new Customer("Bob");
        Customer charlie = new Customer("Charlie");

        Vehicle sedanA = new Sedan("SEDAN-A");
        Vehicle suvB = new SUV("SUV-B");

        rentalSystem.addVehicle(sedanA);
        rentalSystem.addVehicle(suvB);

        Rental rental1 = rentalSystem.rentVehicle(alice, sedanA, 3);
        if (rental1 != null) {
            System.out.println("Sedan A rented successfully by " + alice.getName() + ".");
            System.out.println("Rental charge: $" + rental1.getTotalAmount());
        }

        Rental rental2 = rentalSystem.rentVehicle(bob, sedanA, 2);
        if (rental2 == null) {
            System.out.println("Sedan A is currently unavailable.");
        }

        rentalSystem.returnVehicle(sedanA);
        System.out.println("Sedan A returned by " + alice.getName() + ".");

        Rental rental3 = rentalSystem.rentVehicle(charlie, suvB, 5);
        if (rental3 != null) {
            System.out.println("SUV B rented successfully by " + charlie.getName() + ".");
            System.out.println("Rental charge: $" + rental3.getTotalAmount());
        }
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

abstract class Vehicle {
    private final String vehicleId;
    private boolean rented;

    public Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public abstract double calculateRentalCharge(int days);
}

class Sedan extends Vehicle {
    public Sedan(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 45.0;
    }
}

class SUV extends Vehicle {
    public SUV(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 65.0;
    }
}

class Truck extends Vehicle {
    public Truck(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 90.0;
    }
}

class Rental {
    private final Customer customer;
    private final Vehicle vehicle;
    private final int days;
    private final double totalAmount;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.totalAmount = vehicle.calculateRentalCharge(days);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getDays() {
        return days;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

class VehicleRentalSystem {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<Rental> rentals = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
        if (days <= 0) {
            System.out.println("Rental duration must be positive.");
            return null;
        }

        if (vehicle.isRented()) {
            return null;
        }

        vehicle.setRented(true);
        Rental rental = new Rental(customer, vehicle, days);
        rentals.add(rental);
        return rental;
    }

    public void returnVehicle(Vehicle vehicle) {
        if (!vehicle.isRented()) {
            System.out.println(vehicle.getVehicleId() + " is already available.");
            return;
        }

        vehicle.setRented(false);
    }
}
