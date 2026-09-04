public class TrafficStreakAnalyzer {

    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("No signal data provided");
            return;
        }

        int currentStreak = 1;
        int longestStreak = 1;
        char longestColor = signalLog.charAt(0);

        // Traverse through the string
        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentStreak++;
            } else {
                currentStreak = 1;
            }

            // Update longest streak if current streak is longer
            if (currentStreak > longestStreak) {
                longestStreak = currentStreak;
                longestColor = signalLog.charAt(i);
            }
        }

        System.out.printf("Longest Streak: '%c' repeated %d times%n", longestColor, longestStreak);
    }

    public static void main(String[] args) {
        // Test cases
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
        findLongestStreak("YYYYYRRRGGG");
    }
}