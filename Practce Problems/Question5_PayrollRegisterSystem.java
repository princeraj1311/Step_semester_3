import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Question5_PayrollRegisterSystem {
    static abstract class Staff {
        protected String name;
        protected String type;

        Staff(String name, String type) {
            this.name = name;
            this.type = type;
        }

        abstract double calculatePay();

        public String toString() {
            return "Payslip[name=" + name + ", type=" + type + ", pay=" + (int) calculatePay() + "]";
        }
    }

    static class FullTimeStaff extends Staff {
        private double salary;

        FullTimeStaff(String name, double salary) {
            super(name, "FullTime");
            this.salary = salary;
        }

        double calculatePay() {
            return salary;
        }
    }

    static class PartTimeStaff extends Staff {
        private double hours;
        private double rate;

        PartTimeStaff(String name, double hours, double rate) {
            super(name, "PartTime");
            this.hours = hours;
            this.rate = rate;
        }

        double calculatePay() {
            return hours * rate;
        }
    }

    static class InternStaff extends Staff {
        private double stipend;

        InternStaff(String name, double stipend) {
            super(name, "Intern");
            this.stipend = stipend;
        }

        double calculatePay() {
            return stipend;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Staff> staffList = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            String[] parts = trimmed.split("\\s+");
            String type = parts[0];

            if (type.equals("FullTime")) {
                String name = parts[1];
                double salary = Double.parseDouble(parts[2]);
                staffList.add(new FullTimeStaff(name, salary));
            } else if (type.equals("PartTime")) {
                String name = parts[1];
                double hours = Double.parseDouble(parts[2]);
                double rate = Double.parseDouble(parts[3]);
                staffList.add(new PartTimeStaff(name, hours, rate));
            } else if (type.equals("Intern")) {
                String name = parts[1];
                double stipend = Double.parseDouble(parts[2]);
                staffList.add(new InternStaff(name, stipend));
            }
        }

        double totalPay = 0;
        Staff topStaff = null;
        for (Staff staff : staffList) {
            System.out.println(staff);
            totalPay += staff.calculatePay();
            if (topStaff == null || staff.calculatePay() > topStaff.calculatePay()) {
                topStaff = staff;
            }
        }

        System.out.println("Total " + (int) totalPay);
        System.out.println("top earner " + topStaff.name);
    }
}
