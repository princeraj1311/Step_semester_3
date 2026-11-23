import java.util.Scanner;

class Entry {
    String name;
    int marks;

    Entry(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class Problem5 {
    public static Entry[] leaderboard(Entry[] entries) {
        if (entries == null) {
            return new Entry[0];
        }

        int[] count = new int[101];
        for (Entry entry : entries) {
            count[entry.marks]++;
        }

        int[] position = new int[101];
        int offset = 0;
        for (int marks = 100; marks >= 0; marks--) {
            position[marks] = offset;
            offset += count[marks];
        }

        Entry[] result = new Entry[entries.length];
        for (int i = 0; i < entries.length; i++) {
            int marks = entries[i].marks;
            result[position[marks]] = entries[i];
            position[marks]++;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 90),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };

        if (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().trim().split("\\s+");
            if (parts.length >= 10 && parts.length % 2 == 0) {
                entries = new Entry[parts.length / 2];
                for (int i = 0; i < entries.length; i++) {
                    entries[i] = new Entry(parts[2 * i],
                            Integer.parseInt(parts[2 * i + 1].replace(",", "")));
                }
            }
        }

        Entry[] result = leaderboard(entries);
        for (int i = 0; i < result.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(result[i].name + " " + result[i].marks);
        }
        System.out.println();
        scanner.close();
    }
}
