class M2_PayrollBatchBonus {

    static class Employee {
        String id;
        double salary;

        public Employee(String id, double salary) {
            this.id = id;
            this.salary = salary;
        }

        public void raiseSalary(double salary) {
            this.salary += salary;
        }
    }

    public static void main(String[] args) {
        String[] ids = {"E-101", "E-102", "E-103", "E-104"};
        double[] startingSalaries = {40000, 55000, 62000, 48000};
        double bonus = 5000;

        for (int i = 0; i < ids.length; i++) {
            Employee emp = new Employee(ids[i], startingSalaries[i]);
            emp.raiseSalary(bonus);
            System.out.println(emp.id + " | Final Salary: Rs " + emp.salary);
        }
    }
}