public class Problem9 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node insertAtEndCircular(Node head, int value) {
        Node newNode = new Node(value);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }

        Node tail = head;
        while (tail.next != head) {
            tail = tail.next;
        }
        tail.next = newNode;
        newNode.next = head;
        return head;
    }

    static void printCircular(Node head) {
        if (head == null) {
            System.out.println();
            return;
        }
        Node current = head;
        do {
            System.out.print(current.data);
            if (current.next != head) {
                System.out.print(" -> ");
            }
            current = current.next;
        } while (current != head);
        System.out.println(" -> (back to " + head.data + ")");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = head;
        printCircular(insertAtEndCircular(head, 4));
    }
}
