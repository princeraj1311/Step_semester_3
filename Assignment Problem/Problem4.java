import java.util.Scanner;

class TabNode {
    String data;
    TabNode prev;
    TabNode next;

    TabNode(String data) {
        this.data = data;
    }
}

class BrowserTabStrip {
    TabNode head;
    TabNode tail;
    TabNode current;

    void open(String name) {
        TabNode tab = new TabNode(name);
        tab.prev = current;
        if (current != null) {
            tab.next = current.next;
            if (current.next != null) {
                current.next.prev = tab;
            } else {
                tail = tab;
            }
            current.next = tab;
        } else {
            head = tail = tab;
        }
        current = tab;
    }

    void close() {
        if (current == null) {
            return;
        }

        TabNode before = current.prev;
        TabNode after = current.next;
        if (before != null) {
            before.next = after;
        } else {
            head = after;
        }
        if (after != null) {
            after.prev = before;
        } else {
            tail = before;
        }
        current = after != null ? after : before;
    }

    void back() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
    }

    void forward() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    void printStrip() {
        TabNode tab = head;
        while (tab != null) {
            if (tab == current) {
                System.out.print("[" + tab.data + "]");
            } else {
                System.out.print(tab.data);
            }
            if (tab.next != null) {
                System.out.print(" <-> ");
            }
            tab = tab.next;
        }
        System.out.println();
    }
}

public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BrowserTabStrip strip = new BrowserTabStrip();

        if (!scanner.hasNextLine()) {
            scanner.close();
            return;
        }

        String[] parts = scanner.nextLine().trim().split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            String command = parts[i];
            if (command.equals("OPEN")) {
                if (i + 1 < parts.length) {
                    i++;
                    String name = parts[i].replace(",", "");
                    strip.open(name);
                }
            } else if (command.equals("CLOSE")) {
                strip.close();
            } else if (command.equals("BACK")) {
                strip.back();
            } else if (command.equals("FORWARD")) {
                strip.forward();
            } else {
                continue;
            }

            System.out.print(command + ": ");
            strip.printStrip();
        }
        scanner.close();
    }
}
