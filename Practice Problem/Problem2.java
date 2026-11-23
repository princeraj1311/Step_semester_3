import java.util.Scanner;

public class Problem2 {
    public static String shiftNotice(String text, int k) {
        k %= 26;
        StringBuilder shifted = new StringBuilder(text.length());

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                shifted.append((char) ('a' + (ch - 'a' + k) % 26));
            } else if (ch >= 'A' && ch <= 'Z') {
                shifted.append((char) ('A' + (ch - 'A' + k) % 26));
            } else {
                shifted.append(ch);
            }
        }
        return shifted.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            scanner.close();
            return;
        }
        String text = scanner.nextLine();
        int k = scanner.hasNextInt() ? scanner.nextInt() : 0;
        System.out.println(shiftNotice(text, k));
        scanner.close();
    }
}
