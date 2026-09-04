public class MovieReviewProfiler {

    public static void classifyWordLengths(String review) {
        if (review == null || review.trim().isEmpty()) {
            System.out.println("No review provided");
            return;
        }

        // Split review into words (split by spaces)
        String[] words = review.split("\\s+");

        int shortCount = 0;  // 1-4 letters
        int mediumCount = 0; // 5-8 letters
        int longCount = 0;   // 9+ letters

        // Classify each word
        for (String word : words) {
            // Remove punctuation for accurate length counting
            String cleanWord = word.replaceAll("[^a-zA-Z]", "");

            if (cleanWord.isEmpty()) continue;

            int length = cleanWord.length();

            if (length >= 1 && length <= 4) {
                shortCount++;
            } else if (length >= 5 && length <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d%n", shortCount, mediumCount, longCount);
    }

    public static void main(String[] args) {
        // Test cases
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
        System.out.println();
        classifyWordLengths("A great film with wonderful acting and superb direction");
        System.out.println();
        classifyWordLengths("Amazing spectacular extraordinary cinematography");
    }
}
