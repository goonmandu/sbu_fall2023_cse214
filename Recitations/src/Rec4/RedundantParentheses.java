package Rec4;

import java.util.Stack;

public class RedundantParentheses {

    public static boolean hasRedundantParentheses(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (ch != ')') {
                stack.push(ch);
            } else {
                if (stack.peek() == '(') {
                    return true;
                }
                while (!stack.isEmpty() && stack.peek() != '(') {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    stack.pop();  // Pop the matching '('
                }
            }
        }

        return false;  // No redundant parentheses found
    }

    public static void main(String[] args) {
        String expression1 = "(2+((3+4)))";
        String expression2 = "((x+y)) + z";
        String expression3 = "(x+y)";

        System.out.println("Expression 1: " + hasRedundantParentheses(expression1));
        System.out.println("Expression 2: " + hasRedundantParentheses(expression2));
        System.out.println("Expression 3: " + hasRedundantParentheses(expression3));
    }
}