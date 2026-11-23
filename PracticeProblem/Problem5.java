public class Problem5 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node splitListInHalf(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = slow.next;
        slow.next = null;
        return secondHalf;
    }

    static void print(String label, Node head) {
        System.out.print(label);
        if (head == null) {
            System.out.println("(empty)");
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

        Node second = splitListInHalf(head);
        print("First half: ", head);
        print("Returned: ", second);
    }
}
