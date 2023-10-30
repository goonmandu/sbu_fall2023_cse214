package Rec4;

import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println("This should be false: " + isValidlyParenthesized("{[}}]]"));
        System.out.println("This should be true:  " + isValidlyParenthesized("(){[()]}[]"));
        System.out.println("This should be false: " + isValidlyParenthesized("[{}]()([]"));
    }

    public static boolean isValidlyParenthesized(String input) {
        Stack<Character> stack = new Stack<>();
        for (var c : input.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else if (stack.peek() == '{') {
                if (c == ')' || c == ']') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (stack.peek() == '(') {
                if (c == '}' || c == ']') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (stack.peek() == '[') {
                if (c == ')' || c == '}') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
}
