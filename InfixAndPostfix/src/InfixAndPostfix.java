import java.util.Stack;
import java.util.LinkedList;

public class InfixAndPostfix {
    public static int operatorPriority(char c) {
        if (c == '^') {
            return 3;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        } else if (c == '(' || c == ')') {
            return 0;
        } else {
            return -1;
        }
    }
    public static String inToPost(String str) {
        Stack<Character> s = new Stack<>();
        String ret = "";
        for (var c : str.toCharArray()) {
            int op = operatorPriority(c);
            if (op == -1) {  // if operand
                ret += c;
            } else {         // if operator
                if (s.isEmpty() || c == '(') {  // if stack is empty or c is (, push
                    s.push(c);
                } else if (operatorPriority(s.peek()) > 0 && c != ')') {  // if latest is math operator
                    if (operatorPriority(s.peek()) > op) {  // and it is superior to c
                        s.push(c);  // push
                    } else {  // if same or inferior
                        while (operatorPriority(s.peek()) != 0) {  // until compatible operator,
                            ret += s.pop();  // pop stack into ret
                        }
                        s.push(c);  // finally, push
                    }
                } else {  // if latest is closing paren,
                    if (c == ')') {
                        while (s.peek() != '(') {
                            ret += s.pop();
                        }
                        s.pop();
                    } else {
                        s.push(c);
                    }
                }
            }
        }
        while (!s.isEmpty()) {
            ret += s.pop();
        }
        return ret;
    }
    public static void main(String[] args) {
        System.out.println(inToPost("(A+B)/(C-D)-(E*F)"));
    }
}
