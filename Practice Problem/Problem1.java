import java.util.Scanner;
import java.util.Stack;

public class Problem1 {
    private static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        }
        if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0;
    }

    public static String toPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            if (Character.isDigit(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(operators.pop());
                }
                if (!operators.isEmpty()) {
                    operators.pop();
                }
            } else {
                while (!operators.isEmpty()
                        && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(ch)) {
                    postfix.append(operators.pop());
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            postfix.append(operators.pop());
        }
        return postfix.toString();
    }

    public static int evaluate(String postfix) {
        Stack<Integer> values = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            if (Character.isDigit(ch)) {
                values.push(ch - '0');
            } else {
                int right = values.pop();
                int left = values.pop();
                switch (ch) {
                    case '+':
                        values.push(left + right);
                        break;
                    case '-':
                        values.push(left - right);
                        break;
                    case '*':
                        values.push(left * right);
                        break;
                    case '/':
                        if (right == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        values.push(left / right);
                        break;
                }
            }
        }
        return values.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String infix = scanner.nextLine().trim();
            if (infix.isEmpty()) {
                continue;
            }
            String postfix = toPostfix(infix);
            System.out.println(postfix + " = " + evaluate(postfix));
        }
        scanner.close();
    }
}
