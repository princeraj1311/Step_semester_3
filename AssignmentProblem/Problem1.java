import java.util.Scanner;

public class Problem1 {
    public static int[] bubbleStats(int[] scores) {
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < scores.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < scores.length - i - 1; j++) {
                if (scores[j] > scores[j + 1]) {
                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            passes++;
            if (!swapped) {
                break;
            }
        }

        return new int[]{passes, swaps};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] scores = {5, 1, 4, 2, 8};

        if (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String[] values = line.split("[\\s,]+");
                scores = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    scores[i] = Integer.parseInt(values[i]);
                }
            }
        }

        int[] stats = bubbleStats(scores);
        for (int i = 0; i < scores.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(scores[i]);
        }
        System.out.println();
        System.out.println("passes = " + stats[0] + ", swaps = " + stats[1]);
        scanner.close();
    }
}
