public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        // Ensure strings have same length
        if (original.length() != typed.length()) {
            System.out.println("Error: Strings must be of equal length");
            return;
        }

        int totalChars = original.length();
        int matchedCount = 0;
        int firstMismatchPos = -1;

        // Compare character by character
        for (int i = 0; i < totalChars; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matchedCount++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i;
            }
        }

        // Calculate accuracy percentage
        double accuracy = (double) matchedCount / totalChars * 100;

        // Print results
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", matchedCount, totalChars, accuracy);

        if (firstMismatchPos == -1) {
            System.out.println(" | No Mismatches");
        } else {
            System.out.printf(" | First Mismatch at position %d ('%c' vs '%c')%n",
                    firstMismatchPos + 1,
                    original.charAt(firstMismatchPos),
                    typed.charAt(firstMismatchPos));
        }
    }

    public static void main(String[] args) {
        // Test cases
        checkTypingAccuracy("hello world", "hello world");
        System.out.println();
        checkTypingAccuracy("coding", "coding");
        System.out.println();
        checkTypingAccuracy("hello world", "hello world");
    }
}