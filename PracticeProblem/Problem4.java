public class Problem4 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node deleteNthNode(Node head, int n) {
        if (n == 1) {
            return head.next;
        }

        Node current = head;
        for (int i = 1; i < n - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        return head;
    }

    static void print(Node head) {
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.data + " -> ");
            } else {
                System.out.println(head.data);
            }
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        print(deleteNthNode(head, 3));
    }
}
