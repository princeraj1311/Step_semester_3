import java.util.Scanner;

class Problem2Node {
    int data;
    Problem2Node next;

    Problem2Node(int data) {
        this.data = data;
    }
}

public class Problem2 {
    public static String kthFromEnd(Problem2Node head, int k) {
        if (k <= 0) {
            return "Invalid k";
        }

        Problem2Node lead = head;
        for (int i = 0; i < k; i++) {
            if (lead == null) {
                return "Invalid k";
            }
            lead = lead.next;
        }

        Problem2Node trail = head;
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        return trail == null ? "Invalid k" : Integer.toString(trail.data);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().trim();
        int k = Integer.parseInt(scanner.nextLine().trim());

        if (line.isEmpty()) {
            System.out.println("Invalid k");
            return;
        }

        line = line.replace("->", " ");
        String[] values = line.split("\\s+");
        Problem2Node head = new Problem2Node(Integer.parseInt(values[0]));
        Problem2Node tail = head;
        for (int i = 1; i < values.length; i++) {
            tail.next = new Problem2Node(Integer.parseInt(values[i]));
            tail = tail.next;
        }

        String result = kthFromEnd(head, k);
        if (result.equals("Invalid k")) {
            System.out.println("Invalid k");
        } else {
            System.out.println(k + " from the end: " + result);
        }
        scanner.close();
    }
}
