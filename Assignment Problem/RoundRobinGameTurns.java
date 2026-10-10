public class RoundRobinGameTurns {
    static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    static class CircularGameList {
        private Node head;
        private Node tail;

        public void addLast(String name) {
            Node newNode = new Node(name);
            if (head == null) {
                head = newNode;
                tail = newNode;
                tail.next = head;
                return;
            }

            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }

        public void printTurns(int turns) {
            if (head == null || turns <= 0) {
                System.out.println("No turns to print.");
                return;
            }

            Node current = head;
            for (int i = 0; i < turns; i++) {
                System.out.print(current.name);
                if (i < turns - 1) {
                    System.out.print(" ");
                }
                current = current.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CircularGameList players = new CircularGameList();
        players.addLast("Asha");
        players.addLast("Ravi");
        players.addLast("Neha");

        System.out.println("Turn order for 7 turns:");
        players.printTurns(7);
    }
}
