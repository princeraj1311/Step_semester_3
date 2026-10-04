public class Question4HotWeatherAlertWindows {
    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || readings.length == 0 || k <= 0 || k > readings.length) {
            return 0;
        }

        long windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }

        int alertCount = 0;
        long requiredSum = (long) k * threshold;

        if (windowSum >= requiredSum) {
            alertCount++;
        }

        for (int end = k; end < readings.length; end++) {
            windowSum += readings[end] - readings[end - k];
            if (windowSum >= requiredSum) {
                alertCount++;
            }
        }

        return alertCount;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        System.out.println(countAlerts(readings, 3, 4));
    }
}
