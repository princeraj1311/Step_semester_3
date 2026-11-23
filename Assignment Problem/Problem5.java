import java.util.Scanner;

class MarkNode {
    int data;
    MarkNode next;

    MarkNode(int data) {
        this.data = data;
    }
}

public class Problem5 {
    public static MarkNode partition(MarkNode head, int passMark) {
        MarkNode belowHead = null;
        MarkNode belowTail = null;
        MarkNode restHead = null;
        MarkNode restTail = null;

        while (head != null) {
            MarkNode next = head.next;
            head.next = null;
            if (head.data < passMark) {
                if (belowHead == null) {
                    belowHead = head;
                } else {
                    belowTail.next = head;
                }
                belowTail = head;
            } else {
                if (restHead == null) {
                    restHead = head;
                } else {
                    restTail.next = head;
                }
                restTail = head;
            }
            head = next;
        }

        if (belowHead == null) {
            return restHead;
        }
        belowTail.next = restHead;
        return belowHead;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().trim();
        int passMark = Integer.parseInt(scanner.nextLine().trim());

        if (line.isEmpty()) {
            System.out.println("null");
            return;
        }

        line = line.replace("->", " ");
        String[] values = line.split("\\s+");
        MarkNode head = new MarkNode(Integer.parseInt(values[0]));
        MarkNode tail = head;
        for (int i = 1; i < values.length; i++) {
            tail.next = new MarkNode(Integer.parseInt(values[i]));
            tail = tail.next;
        }

        MarkNode result = partition(head, passMark);
        while (result != null) {
            System.out.print(result.data);
            if (result.next != null) {
                System.out.print(" -> ");
            }
            result = result.next;
        }
        System.out.println();
        scanner.close();
    }
}
