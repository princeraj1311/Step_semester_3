public class CancelledOrdersCleanup {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class OrderList {
        public static Node removeAll(Node head, int code) {
            while (head != null && head.data == code) {
                head = head.next;
            }

            Node current = head;
            Node previous = null;

            while (current != null) {
                if (current.data == code) {
                    if (previous != null) {
                        previous.next = current.next;
                    }
                    current = current.next;
                } else {
                    previous = current;
                    current = current.next;
                }
            }

            return head;
        }

        public static void printList(Node head) {
            if (head == null) {
                System.out.println("null");
                return;
            }

            Node current = head;
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) {
                    System.out.print(" -> ");
                }
                current = current.next;
            }
            System.out.println(" -> null");
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(5);
        head1.next = new Node(3);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);

        System.out.println("Sample 1:");
        Node result1 = OrderList.removeAll(head1, 5);
        OrderList.printList(result1);

        Node head2 = new Node(5);
        head2.next = new Node(5);
        head2.next.next = new Node(5);

        System.out.println("Sample 2:");
        Node result2 = OrderList.removeAll(head2, 5);
        OrderList.printList(result2);
    }
}
