public class Problem3 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node removeFirstKNodes(Node head, int k) {
        while (head != null && k > 0) {
            head = head.next;
            k--;
        }
        return head;
    }

    static void print(Node head) {
        if (head == null) {
            System.out.println("(empty list)");
            return;
        }
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
        print(removeFirstKNodes(head, 2));
    }
}
