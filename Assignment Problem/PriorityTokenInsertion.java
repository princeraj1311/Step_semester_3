public class PriorityTokenInsertion {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class SinglyLinkedList {
        private Node head;

        public void addFirst(int token) {
            Node newNode = new Node(token);
            newNode.next = head;
            head = newNode;
        }

        public void addLast(int token) {
            Node newNode = new Node(token);
            if (head == null) {
                head = newNode;
                return;
            }

            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        public void insertAt(int index, int token) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Index cannot be negative");
            }

            if (index == 0) {
                addFirst(token);
                return;
            }

            Node newNode = new Node(token);
            Node current = head;
            int position = 0;

            while (current != null && position < index - 1) {
                current = current.next;
                position++;
            }

            if (current == null) {
                throw new IndexOutOfBoundsException("Index out of range");
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        public void printList() {
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
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        System.out.println("Final list:");
        list.printList();
    }
}
