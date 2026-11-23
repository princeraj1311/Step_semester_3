public class Problem10 {
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

    static DNode deleteLastNodeDoubly(DNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            head.prev = null;
            return null;
        }

        DNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        DNode previous = tail.prev;
        previous.next = null;
        tail.prev = null;
        return head;
    }

    static void print(DNode head) {
        if (head == null) {
            System.out.println("(empty list)");
            return;
        }
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.data + " <-> ");
            } else {
                System.out.println(head.data);
            }
            head = head.next;
        }
    }

    public static void main(String[] args) {
        DNode head = new DNode(1);
        head.next = new DNode(2);
        head.next.prev = head;
        head.next.next = new DNode(3);
        head.next.next.prev = head.next;
        print(deleteLastNodeDoubly(head));
    }
}
