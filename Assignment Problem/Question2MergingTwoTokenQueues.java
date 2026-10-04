import java.util.ArrayList;
import java.util.List;

public class Question2MergingTwoTokenQueues {
    public static List<Integer> mergeTokens(int[] counterA, int[] counterB) {
        List<Integer> merged = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                merged.add(counterA[i]);
                i++;
            } else {
                merged.add(counterB[j]);
                j++;
            }
        }

        while (i < counterA.length) {
            merged.add(counterA[i]);
            i++;
        }

        while (j < counterB.length) {
            merged.add(counterB[j]);
            j++;
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] counterA = {3, 8, 15, 20};
        int[] counterB = {5, 8, 12};

        System.out.println(mergeTokens(counterA, counterB));
    }
}
