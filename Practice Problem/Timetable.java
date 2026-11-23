import java.util.ArrayList;
import java.util.List;

public class Timetable {
    private final String name;
    private final List<String> periods;

    public Timetable(String name, List<String> periods) {
        this.name = name;
        this.periods = new ArrayList<>(periods);
    }

    public Timetable copy() {
        return new Timetable(name, periods);
    }

    public Timetable copy(String newName) {
        return new Timetable(newName, periods);
    }

    public void setPeriod(int period, String subject) {
        periods.set(period - 1, subject);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ": " + periods;
    }
}
