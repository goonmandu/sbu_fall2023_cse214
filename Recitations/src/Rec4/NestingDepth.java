package Rec4;

import java.util.Stack;

public class NestingDepth {
    public static void main(String[] args) {
        System.out.println("This should be -1: " + nestingDepth("{[}}]]"));
        System.out.println("This should be  3: " + nestingDepth("(){[()]}[]"));
        System.out.println("This should be -1: " + nestingDepth("[{}]()([]"));
        System.out.println("This should be  0: " + nestingDepth(""));
        System.out.println("This should be  1: " + nestingDepth("()[](){}"));
    }

    public static int nestingDepth(String input) {
        Stack<Character> stack = new Stack<>();
        int deeeeep = 0;
        for (var c : input.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else if (stack.peek() == '{') {
                if (c == ')' || c == ']') {
                    return -1;
                } else {
                    stack.pop();
                }
            } else if (stack.peek() == '(') {
                if (c == '}' || c == ']') {
                    return -1;
                } else {
                    stack.pop();
                }
            } else if (stack.peek() == '[') {
                if (c == ')' || c == '}') {
                    return -1;
                } else {
                    stack.pop();
                }
            }
            if (stack.size() > deeeeep) {
                deeeeep = stack.size();
            }
        }
        if (stack.isEmpty()) {
            return deeeeep;
        } else {
            return -1;
        }
    }
}
