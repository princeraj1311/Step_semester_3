import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            scanner.close();
            return;
        }

        String originalLine = scanner.nextLine().trim();
        String[] tokens = originalLine.split("\\s+");
        String originalName = "CSE-A";
        if (tokens.length >= 2 && tokens[0].equalsIgnoreCase("Timetable")) {
            originalName = tokens[1];
        }
        List<String> periods = new ArrayList<>();
        int bracketStart = originalLine.indexOf('[');
        int bracketEnd = originalLine.indexOf(']');
        if (bracketStart >= 0 && bracketEnd > bracketStart) {
            String[] parts = originalLine.substring(bracketStart + 1, bracketEnd).split(",");
            for (String part : parts) {
                periods.add(part.trim());
            }
        } else {
            while (scanner.hasNext()) {
                periods.add(scanner.next());
                if (periods.size() >= 10) {
                    break;
                }
            }
        }

        String copyName = "CSE-B";
        int periodNumber = 2;
        String subject = "Physics";
        if (scanner.hasNextLine()) {
            String copyLine = scanner.nextLine().trim();
            if (copyLine.contains("CSE-")) {
                copyName = copyLine.substring(copyLine.indexOf("CSE-")).split("\\s+")[0];
            }
        }
        if (scanner.hasNextLine()) {
            String changeLine = scanner.nextLine().trim();
            if (changeLine.contains("period ")) {
                String[] changeTokens = changeLine.split("\\s+");
                for (int i = 0; i < changeTokens.length - 1; i++) {
                    if (changeTokens[i].equals("period")) {
                        periodNumber = Integer.parseInt(changeTokens[i + 1]);
                    }
                }
                int toIndex = changeLine.lastIndexOf("to ");
                if (toIndex >= 0) {
                    subject = changeLine.substring(toIndex + 3).trim();
                }
            }
        }

        Timetable original = new Timetable(originalName, periods);
        Timetable copy = original.copy(copyName);
        copy.setPeriod(periodNumber, subject);

        System.out.println(original);
        System.out.println(copy);
        scanner.close();
    }
}
