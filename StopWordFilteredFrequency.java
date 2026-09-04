import java.util.*;

public class StopWordFilteredFrequency {

    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            System.out.println("No feedback provided");
            return;
        }

        // Stop words list
        String[] stopWordsArray = {"the", "was", "and", "a", "is", "of", "in"};
        Set<String> stopWords = new HashSet<>(Arrays.asList(stopWordsArray));

        // Normalize: convert to lowercase and remove punctuation
        String cleaned = feedback.toLowerCase();
        cleaned = cleaned.replace(".", "");
        cleaned = cleaned.replace(",", "");
        cleaned = cleaned.replace("!", "");
        cleaned = cleaned.replace("?", "");
        cleaned = cleaned.replace(";", "");
        cleaned = cleaned.replace(":", "");

        // Split into words using whitespace pattern
        String[] words = cleaned.split("\\s+");

        // Count frequency of non-stop words
        Map<String, Integer> wordFrequency = new HashMap<>();

        for (String word : words) {
            if (word.isEmpty()) continue;

            // Skip stop words
            if (stopWords.contains(word)) {
                continue;
            }

            // Count frequency
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        // Sort by count in descending order
        List<Map.Entry<String, Integer>> sortedEntries =
                new ArrayList<>(wordFrequency.entrySet());

        sortedEntries.sort((entry1, entry2) ->
                entry2.getValue().compareTo(entry1.getValue()));

        // Print results
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // If no words after filtering
        if (sortedEntries.isEmpty()) {
            System.out.println("No meaningful words found after filtering stop words");
        }
    }

    public static void main(String[] args) {
        // Test case 1
        System.out.println("Test Case 1:");
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");

        System.out.println("\nTest Case 2:");
        printFilteredWordFrequency("The quick brown fox jumps over the lazy dog");

        System.out.println("\nTest Case 3:");
        printFilteredWordFrequency("Java programming is fun and exciting");

        System.out.println("\nTest Case 4:");
        printFilteredWordFrequency("the was and a is of in");
    }
}