import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class Problem1 {
    public static int middle(Node head) {
        if (head == null) {
            return -1;
        }

        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().trim();

        if (line.isEmpty()) {
            System.out.println("Middle: -1");
            return;
        }

        line = line.replace("->", " ");
        String[] values = line.split("\\s+");
        Node head = new Node(Integer.parseInt(values[0]));
        Node tail = head;
        for (int i = 1; i < values.length; i++) {
            tail.next = new Node(Integer.parseInt(values[i]));
            tail = tail.next;
        }

        System.out.println("Middle: " + middle(head));
        scanner.close();
    }
}
