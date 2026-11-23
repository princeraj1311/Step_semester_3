import java.util.Scanner;

class Editor {
    private final String[] history;
    private int top = -1;

    Editor(int capacity) {
        history = new String[capacity];
    }

    void type(String word) {
        if (top == history.length - 1) {
            System.out.println("rejected: history full");
            return;
        }
        history[++top] = word;
        printWords();
    }

    void undo() {
        if (top == -1) {
            System.out.println("rejected: nothing to undo");
            return;
        }
        history[top--] = null;
        printWords();
    }

    private void printWords() {
        if (top == -1) {
            System.out.println("(empty)");
            return;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            if (i > 0) {
                result.append(' ');
            }
            result.append(history[i]);
        }
        System.out.println(result);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int capacity = scanner.nextInt();
        scanner.nextLine();
        Editor editor = new Editor(capacity);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            for (String command : line.split(";")) {
                command = command.trim();
                if (command.startsWith("TYPE ")) {
                    editor.type(command.substring(5).trim());
                } else if (command.equals("UNDO")) {
                    editor.undo();
                }
            }
        }
        scanner.close();
    }
}
