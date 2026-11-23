import java.util.Scanner;

public class Problem5 {
    public static void stableInsertionSort(Participant[] entries) {
        for (int i = 1; i < entries.length; i++) {
            Participant current = entries[i];
            int j = i - 1;
            while (j >= 0 && entries[j].score < current.score) {
                entries[j + 1] = entries[j];
                j--;
            }
            entries[j + 1] = current;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        java.util.ArrayList<Participant> entryList = new java.util.ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            Scanner lineScanner = new Scanner(line);
            if (lineScanner.hasNext()) {
                String name = lineScanner.next();
                if (lineScanner.hasNextInt()) {
                    entryList.add(new Participant(name, lineScanner.nextInt()));
                }
            }
            lineScanner.close();
        }

        Participant[] entries = entryList.toArray(new Participant[0]);
        stableInsertionSort(entries);
        int rank = 0;
        int previousScore = 0;
        for (int i = 0; i < entries.length; i++) {
            if (i == 0 || entries[i].score != previousScore) {
                rank = i + 1;
            }
            System.out.println(rank + " " + entries[i].name + " " + entries[i].score);
            previousScore = entries[i].score;
        }
        scanner.close();
    }
}
