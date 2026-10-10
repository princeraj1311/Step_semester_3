import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Question4_ClassMarksGridAnalysis {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> names = new ArrayList<>();
        List<int[]> marks = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            int start = trimmed.indexOf('[');
            int end = trimmed.lastIndexOf(']');
            String name = trimmed.substring(0, start).trim();
            String values = trimmed.substring(start + 1, end).trim();
            String[] parts = values.split(",");
            int[] studentMarks = new int[parts.length];

            for (int i = 0; i < parts.length; i++) {
                studentMarks[i] = Integer.parseInt(parts[i].trim());
            }

            names.add(name);
            marks.add(studentMarks);
        }

        int[] totals = new int[marks.size()];
        for (int i = 0; i < marks.size(); i++) {
            for (int mark : marks.get(i)) {
                totals[i] += mark;
            }
        }

        int subjectCount = marks.get(0).length;
        double[] averages = new double[subjectCount];
        for (int j = 0; j < subjectCount; j++) {
            int sum = 0;
            for (int i = 0; i < marks.size(); i++) {
                sum += marks.get(i)[j];
            }
            averages[j] = sum / (double) marks.size();
        }

        int topIndex = 0;
        for (int i = 1; i < totals.length; i++) {
            if (totals[i] > totals[topIndex]) {
                topIndex = i;
            }
        }

        StringBuilder totalOutput = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            if (i > 0) {
                totalOutput.append(", ");
            }
            totalOutput.append(names.get(i)).append(" ").append(totals[i]);
        }

        StringBuilder averageOutput = new StringBuilder();
        for (int i = 0; i < averages.length; i++) {
            if (i > 0) {
                averageOutput.append(", ");
            }
            averageOutput.append(String.format("%.2f", averages[i]));
        }

        System.out.println("Totals " + totalOutput);
        System.out.println("averages " + averageOutput);
        System.out.println("topper " + names.get(topIndex) + " (" + totals[topIndex] + ")");
    }
}
