public class ReverseTheQueue {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class QueueList {
        public static Node reverse(Node head) {
            Node previous = null;
            Node current = head;
            Node nextNode;

            while (current != null) {
                nextNode = current.next;
                current.next = previous;
                previous = current;
                current = nextNode;
            }

            return previous;
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
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);

        System.out.println("Sample 1:");
        Node reversed1 = QueueList.reverse(head1);
        QueueList.printList(reversed1);

        Node head2 = new Node(9);

        System.out.println("Sample 2:");
        Node reversed2 = QueueList.reverse(head2);
        QueueList.printList(reversed2);
    }
}
