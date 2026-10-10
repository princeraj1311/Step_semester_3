import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Question2_CampusVehiclePassSystem {
    interface Chargeable {
        boolean charge();
    }

    static abstract class Vehicle {
        protected String passNumber;
        protected String ownerName;
        protected String type;

        Vehicle(String passNumber, String ownerName, String type) {
            this.passNumber = passNumber;
            this.ownerName = ownerName;
            this.type = type;
        }

        abstract double getFee();

        String getPassNumber() {
            return passNumber;
        }

        String getType() {
            return type;
        }
    }

    static class Bike extends Vehicle {
        Bike(String passNumber, String ownerName) {
            super(passNumber, ownerName, "Bike");
        }

        double getFee() {
            return 300;
        }
    }

    static class Car extends Vehicle {
        Car(String passNumber, String ownerName) {
            super(passNumber, ownerName, "Car");
        }

        double getFee() {
            return 1000;
        }
    }

    static class EBike extends Vehicle implements Chargeable {
        EBike(String passNumber, String ownerName) {
            super(passNumber, ownerName, "EBike");
        }

        double getFee() {
            return 300;
        }

        public boolean charge() {
            return true;
        }
    }

    static class ECar extends Vehicle implements Chargeable {
        ECar(String passNumber, String ownerName) {
            super(passNumber, ownerName, "ECar");
        }

        double getFee() {
            return 1000;
        }

        public boolean charge() {
            return true;
        }
    }

    static Vehicle createVehicle(String type, String passNumber, String ownerName) {
        String normalized = type.trim();
        if (normalized.equalsIgnoreCase("Bike")) {
            return new Bike(passNumber, ownerName);
        }
        if (normalized.equalsIgnoreCase("Car")) {
            return new Car(passNumber, ownerName);
        }
        if (normalized.equalsIgnoreCase("EBike") || normalized.equalsIgnoreCase("E-Bike")) {
            return new EBike(passNumber, ownerName);
        }
        if (normalized.equalsIgnoreCase("ECar") || normalized.equalsIgnoreCase("E-Car")) {
            return new ECar(passNumber, ownerName);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Vehicle> vehicles = new HashMap<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            String[] parts = trimmed.split("\\s+");
            String command = parts[0];

            if (command.equals("PASS")) {
                String type = parts[1];
                String passNumber = parts[2];
                String ownerName = parts[3];
                Vehicle vehicle = createVehicle(type, passNumber, ownerName);
                if (vehicle != null) {
                    vehicles.put(passNumber, vehicle);
                    System.out.println(vehicle.getPassNumber() + " (" + vehicle.getType() + ") pass fee " + (int) vehicle.getFee());
                }
            } else if (command.equals("CHARGE")) {
                String passNumber = parts[1];
                Vehicle vehicle = vehicles.get(passNumber);
                if (vehicle instanceof Chargeable) {
                    System.out.println(passNumber + " charging bay allotted");
                } else {
                    System.out.println(passNumber + " rejected: charging unsupported");
                }
            }
        }
    }
}
