public class Question1LaundryQueue {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());
        m1.completeCycle();
        m1.startWash(neha, new NormalWash());
    }
}

class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

interface WashType {
    String getName();
    int getDuration();
    double getCharge();
}

class QuickWash implements WashType {
    @Override
    public String getName() {
        return "Quick";
    }

    @Override
    public int getDuration() {
        return 30;
    }

    @Override
    public double getCharge() {
        return 20.0;
    }
}

class NormalWash implements WashType {
    @Override
    public String getName() {
        return "Normal";
    }

    @Override
    public int getDuration() {
        return 45;
    }

    @Override
    public double getCharge() {
        return 30.0;
    }
}

class HeavyWash implements WashType {
    @Override
    public String getName() {
        return "Heavy";
    }

    @Override
    public int getDuration() {
        return 60;
    }

    @Override
    public double getCharge() {
        return 45.0;
    }
}

class WashCycle {
    private final Student student;
    private final WashingMachine machine;
    private final WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public Student getStudent() {
        return student;
    }

    public WashingMachine getMachine() {
        return machine;
    }

    public WashType getWashType() {
        return washType;
    }
}

class WashingMachine {
    private final String machineId;
    private boolean busy;
    private WashCycle activeCycle;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
    }

    public WashCycle startWash(Student student, WashType washType) {
        if (busy) {
            System.out.println("Machine " + machineId + " is currently busy.");
            return null;
        }

        busy = true;
        activeCycle = new WashCycle(student, this, washType);
        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                washType.getName(), machineId, student.getName(), washType.getDuration(), washType.getCharge());
        return activeCycle;
    }

    public void completeCycle() {
        if (!busy) {
            return;
        }
        System.out.println(machineId + " cycle completed. " + machineId + " is now free.");
        busy = false;
        activeCycle = null;
    }

    public boolean isBusy() {
        return busy;
    }
}
