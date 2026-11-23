import java.util.Scanner;

class Problem3Node {
    int data;
    Problem3Node next;

    Problem3Node(int data) {
        this.data = data;
    }
}

public class Problem3 {
    public static Problem3Node merge(Problem3Node first, Problem3Node second) {
        Problem3Node dummy = new Problem3Node(0);
        Problem3Node tail = dummy;

        while (first != null && second != null) {
            if (first.data <= second.data) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }
            tail = tail.next;
        }

        tail.next = first != null ? first : second;
        return dummy.next;
    }

    private static Problem3Node readList(Scanner scanner) {
        String line = scanner.nextLine().trim();
        line = line.replace("->", " ");
        if (line.isEmpty()) {
            return null;
        }

        line = line.replace("->", " ");
        String[] values = line.split("\\s+");
        Problem3Node head = new Problem3Node(Integer.parseInt(values[0]));
        Problem3Node tail = head;
        for (int i = 1; i < values.length; i++) {
            tail.next = new Problem3Node(Integer.parseInt(values[i]));
            tail = tail.next;
        }
        return head;
    }

    private static void printList(Problem3Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Problem3Node first = readList(scanner);
        Problem3Node second = readList(scanner);
        printList(merge(first, second));
        scanner.close();
    }
}
