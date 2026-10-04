public class TheTalkingToyBox {
    public static void main(String[] args) {
        ToyCar car = new ToyCar("Speedster");
        ToyRobot robot = new ToyRobot("Bolt");

        System.out.println(car.makeSound());
        System.out.println(car.getToyId());
        System.out.println(robot.makeSound());
        System.out.println(robot.getToyId());
    }
}

abstract class Toy {
    private static int nextToyNumber = 1001;
    private final String toyId;

    protected Toy() {
        toyId = "TOY-" + nextToyNumber++;
    }

    public abstract String makeSound();

    public String getToyId() {
        return toyId;
    }
}

class ToyCar extends Toy {
    private final String name;

    public ToyCar(String name) {
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Vroom vroom!";
    }
}

class ToyRobot extends Toy {
    private final String name;

    public ToyRobot(String name) {
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}
