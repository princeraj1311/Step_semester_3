public class Problem6 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node reverseFirstKNodes(Node head, int k) {
        Node prev = null;
        Node current = head;

        for (int i = 0; i < k; i++) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head.next = current;
        return prev;
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
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        print(reverseFirstKNodes(head, 3));
    }
}
