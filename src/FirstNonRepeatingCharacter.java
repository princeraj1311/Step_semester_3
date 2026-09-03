import java.util.*;
public class FirstNonRepeatingCharacter {
    public static char findFirstNonRepeatingChar(String text) {
        // Using HashMap for character frequency
        HashMap<Character, Integer> freqMap = new HashMap<>();
        // Count frequencies
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        // Scan left to right for first character with frequency 1
        for (char c : text.toCharArray()) {
            if (freqMap.get(c) == 1) {
                return c;
            }
        }
        return '\0'; // Return null character if none found
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a word or sentence: ");
        String input = sc.nextLine();
        char result = findFirstNonRepeatingChar(input);
        if (result != '\0') {
            System.out.printf("First Non-Repeating Character: \"%c\"%n", result);
        } else {
            System.out.println("No Non-Repeating Character Found");
        }
    }
}
