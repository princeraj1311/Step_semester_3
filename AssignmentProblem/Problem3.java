import java.util.Scanner;

public class Problem3 {
    public static long countInversions(int[] roll) {
        int[] temp = new int[roll.length];
        return mergeCount(roll, temp, 0, roll.length - 1);
    }

    private static long mergeCount(int[] roll, int[] temp, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        long count = mergeCount(roll, temp, left, mid);
        count += mergeCount(roll, temp, mid + 1, right);
        count += mergeSorted(roll, temp, left, mid, right);
        return count;
    }

    private static long mergeSorted(int[] roll, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = roll[i];
        }

        long count = 0;
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                roll[k++] = temp[i++];
            } else {
                roll[k++] = temp[j++];
                count += (mid - i + 1);
            }
        }
        while (i <= mid) {
            roll[k++] = temp[i++];
        }
        while (j <= right) {
            roll[k++] = temp[j++];
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] roll = {2, 4, 1, 3, 5};

        if (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String[] values = line.split("[\\s,]+");
                roll = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    roll[i] = Integer.parseInt(values[i]);
                }
            }
        }

        System.out.println(countInversions(roll));
        scanner.close();
    }
}
