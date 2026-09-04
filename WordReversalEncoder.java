public class WordReversalEncoder {

    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }

        // Split into words using split(" ")
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        // Process each word
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // Build reversed word using StringBuilder
            StringBuilder reversedWord = new StringBuilder();
            for (int j = word.length() - 1; j >= 0; j--) {
                reversedWord.append(word.charAt(j));
            }

            // Alternative: use StringBuilder's built-in reverse()
            // String reversed = new StringBuilder(word).reverse().toString();

            // Add reversed word to result
            result.append(reversedWord);

            // Add space between words (except after last word)
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(reverseEachWord("hello club"));
        System.out.println(reverseEachWord("Java Programming is fun"));
        System.out.println(reverseEachWord("a b c"));
        System.out.println(reverseEachWord(""));
    }
}