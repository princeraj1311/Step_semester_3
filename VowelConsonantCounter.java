public class VowelConsonantCounter {

    public static void countVowelsAndConsonants(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("No text provided");
            return;
        }

        int vowelCount = 0;
        int consonantCount = 0;

        // Convert to lowercase for case-insensitive comparison
        String lowerText = text.toLowerCase();

        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);

            // Skip spaces
            if (ch == ' ') {
                continue;
            }

            // Check if character is a letter
            if (ch >= 'a' && ch <= 'z') {
                // Check for vowels
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
        }

        System.out.printf("Vowels: %d | Consonants: %d%n", vowelCount, consonantCount);
    }

    public static void main(String[] args) {
        // Test cases
        countVowelsAndConsonants("Java Programming");
        countVowelsAndConsonants("Hello World");
        countVowelsAndConsonants("A E I O U");
    }
}