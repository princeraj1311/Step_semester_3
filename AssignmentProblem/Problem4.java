import java.util.Scanner;

public class Problem4 {
    public static int kthHighest(int[] marks, int k) {
        if (marks == null || k < 1 || k > marks.length) {
            throw new IllegalArgumentException("k must be between 1 and marks.length");
        }

        int low = 0;
        int high = marks.length - 1;
        int target = marks.length - k;

        while (low <= high) {
            int pivotIndex = partition(marks, low, high);
            if (pivotIndex == target) {
                return marks[pivotIndex];
            }
            if (pivotIndex < target) {
                low = pivotIndex + 1;
            } else {
                high = pivotIndex - 1;
            }
        }
        throw new IllegalArgumentException("Unable to find kth highest mark");
    }

    private static int partition(int[] marks, int low, int high) {
        int pivot = marks[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (marks[j] <= pivot) {
                int temp = marks[i];
                marks[i] = marks[j];
                marks[j] = temp;
                i++;
            }
        }

        marks[high] = marks[i];
        marks[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] marks = {72, 95, 60, 88, 79};
        int k = 2;

        if (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String[] values = line.split("[\\s,]+");
                marks = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    marks[i] = Integer.parseInt(values[i]);
                }
            }
        }
        if (scanner.hasNextLine()) {
            k = Integer.parseInt(scanner.nextLine().trim());
        }

        System.out.println(kthHighest(marks, k));
        scanner.close();
    }
}
