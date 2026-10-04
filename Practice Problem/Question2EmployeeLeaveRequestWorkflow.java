import java.time.LocalDate;

public class Question2EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("E101", "John");
        Employee jane = new PartTimeEmployee("E202", "Jane");

        LeaveManager manager = new LeaveManager();

        LeaveRequest request1 = manager.submitLeaveRequest(john, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        System.out.println("Leave request submitted for " + john.getName() + " (Jan 1-5). Status: " + request1.getStatus());

        manager.approveRequest(request1);
        System.out.println("John's leave request (Jan 1-5) approved. Status: " + request1.getStatus());

        LeaveRequest request2 = manager.submitLeaveRequest(jane, LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 11));
        System.out.println("Leave request submitted for " + jane.getName() + " (Feb 10-11). Status: " + request2.getStatus());

        manager.rejectRequest(request2);
        System.out.println("Jane's leave request (Feb 10-11) rejected. Status: " + request2.getStatus());

        System.out.println("Attempting to change approved leave back to pending...");
        request1.setStatus(LeaveStatus.PENDING);
        System.out.println("Cannot change leave request status from Approved to Pending.");
    }
}

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

abstract class Employee {
    private final String employeeId;
    private final String name;

    public Employee(String employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public abstract int getMaxLeaveDays();

    public abstract boolean canApplyLeave(int days);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String employeeId, String name) {
        super(employeeId, name);
    }

    @Override
    public int getMaxLeaveDays() {
        return 15;
    }

    @Override
    public boolean canApplyLeave(int days) {
        return days <= getMaxLeaveDays();
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String employeeId, String name) {
        super(employeeId, name);
    }

    @Override
    public int getMaxLeaveDays() {
        return 7;
    }

    @Override
    public boolean canApplyLeave(int days) {
        return days <= getMaxLeaveDays();
    }
}

class Contractor extends Employee {
    public Contractor(String employeeId, String name) {
        super(employeeId, name);
    }

    @Override
    public int getMaxLeaveDays() {
        return 3;
    }

    @Override
    public boolean canApplyLeave(int days) {
        return days <= getMaxLeaveDays();
    }
}

class LeaveRequest {
    private final Employee employee;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private LeaveStatus status;

    public LeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus newStatus) {
        if (this.status == LeaveStatus.APPROVED || this.status == LeaveStatus.REJECTED) {
            if (newStatus == LeaveStatus.PENDING) {
                System.out.println("Cannot change leave request status from " + this.status + " to Pending.");
                return;
            }
        }
        this.status = newStatus;
    }
}

class LeaveManager {
    public LeaveRequest submitLeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;

        if (!employee.canApplyLeave((int) days)) {
            throw new IllegalArgumentException(employee.getName() + " cannot apply for this many leave days.");
        }

        LeaveRequest request = new LeaveRequest(employee, startDate, endDate);
        System.out.println("Leave request submitted for " + employee.getName() + " (" + startDate + " to " + endDate + ").");
        return request;
    }

    public void approveRequest(LeaveRequest request) {
        request.setStatus(LeaveStatus.APPROVED);
    }

    public void rejectRequest(LeaveRequest request) {
        request.setStatus(LeaveStatus.REJECTED);
    }
}
