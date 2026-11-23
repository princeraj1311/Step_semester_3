public class Problem2 {
    static class DNode {
        int data;
        DNode prev;
        DNode next;

        DNode(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    static DNode insertAtTailDoubly(DNode head, int value) {
        DNode newNode = new DNode(value);
        if (head == null) {
            return newNode;
        }

        DNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newNode;
        newNode.prev = tail;
        return head;
    }

    static void print(DNode head) {
        DNode current = head;
        while (current != null) {
            if (current != head) {
                System.out.print(" <-> ");
            }
            System.out.print(current.data);
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DNode head = new DNode(1);
        head.next = new DNode(2);
        head.next.prev = head;
        head.next.next = new DNode(3);
        head.next.next.prev = head.next;
        print(insertAtTailDoubly(head, 4));
    }
}
