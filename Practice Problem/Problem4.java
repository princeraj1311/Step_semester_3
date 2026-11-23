import java.util.Scanner;

public class Problem4 {
    public static int lowerBound(int[] scores, int x) {
        int low = 0;
        int high = scores.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (scores[mid] >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            scanner.close();
            return;
        }

        String firstLine = scanner.nextLine().trim()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
        java.util.ArrayList<Integer> scoresList = new java.util.ArrayList<>();
        Scanner first = new Scanner(firstLine);
        while (first.hasNextInt()) {
            scoresList.add(first.nextInt());
        }
        first.close();

        int[] scores = new int[scoresList.size()];
        for (int i = 0; i < scoresList.size(); i++) {
            scores[i] = scoresList.get(i);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            line = line.replaceFirst("(?i)^QUERY\\s*", "").trim();
            Scanner lineScanner = new Scanner(line);
            if (!lineScanner.hasNextInt()) {
                lineScanner.close();
                continue;
            }
            int x = lineScanner.nextInt();
            lineScanner.close();
            int below = lowerBound(scores, x);
            int exactly = lowerBound(scores, x + 1) - below;
            System.out.println(x + ": exactly " + exactly + ", below " + below);
        }
        scanner.close();
    }
}
