public class TrainCoachesBothWays {
    static class Node {
        String name;
        Node prev;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    static class Train {
        private Node head;
        private Node tail;

        public void addLast(String name) {
            Node newNode = new Node(name);
            if (head == null) {
                head = newNode;
                tail = newNode;
                return;
            }

            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        public void remove(String name) {
            Node current = head;
            while (current != null) {
                if (current.name.equals(name)) {
                    if (current == head && current == tail) {
                        head = null;
                        tail = null;
                    } else if (current == head) {
                        head = current.next;
                        head.prev = null;
                    } else if (current == tail) {
                        tail = current.prev;
                        tail.next = null;
                    } else {
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
                    }
                    return;
                }
                current = current.next;
            }
        }

        public void printForward() {
            Node current = head;
            StringBuilder sb = new StringBuilder();
            while (current != null) {
                sb.append(current.name);
                if (current.next != null) {
                    sb.append(" <-> ");
                }
                current = current.next;
            }
            System.out.println("Forward: " + sb.toString());
        }

        public void printBackward() {
            Node current = tail;
            StringBuilder sb = new StringBuilder();
            while (current != null) {
                sb.append(current.name);
                if (current.prev != null) {
                    sb.append(" <-> ");
                }
                current = current.prev;
            }
            System.out.println("Backward: " + sb.toString());
        }
    }

    public static void main(String[] args) {
        Train train = new Train();
        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        train.printForward();
        train.printBackward();
    }
}
