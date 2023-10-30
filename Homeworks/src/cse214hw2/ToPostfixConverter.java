package cse214hw2;

import java.util.Stack;

public class ToPostfixConverter implements Converter {
    public String convert(ArithmeticExpression exp) {
        StringBuilder ret = new StringBuilder();
        Stack<Operator> s = new Stack<>();
        for (var c : tokensOf(exp.getExpression())) {
            if (Operator.isOperator(c)) {
                if (s.isEmpty() || s.peek().getSymbol() == Operator.LEFT_PARENTHESIS.getSymbol()) {
                    s.push(Operator.of(c));
                } else {
                    var top = s.peek();
                    var op = Operator.of(c);
                    operatorRankBehavior(ret, s, top, op);
                }
            } else if (isParenthesis(c)) {
                if (Operator.of(c) == Operator.RIGHT_PARENTHESIS) {
                    while (s.peek().getSymbol() != Operator.LEFT_PARENTHESIS.getSymbol()) {
                        ret.append(s.pop().getSymbol());
                        ret.append(" ");
                    }
                    s.pop();
                } else {
                    s.push(Operator.LEFT_PARENTHESIS);
                }
            } else {
                ret.append(c); ret.append(" ");
            }
        }

        while (!s.isEmpty()) {
            var popped = s.pop().getSymbol();
            if (popped != Operator.LEFT_PARENTHESIS.getSymbol() &&
                popped != Operator.RIGHT_PARENTHESIS.getSymbol()) {
                ret.append(popped); ret.append(" ");
            }
        }

        return ret.toString();
    }

    private void operatorRankBehavior(StringBuilder ret, Stack<Operator> s, Operator top, Operator op) {
        if (op.getRank() < top.getRank()) {
            s.push(op);
        } else if (op.getRank() == top.getRank()) {
            ret.append(s.pop().getSymbol()); ret.append(" ");
            s.push(op);
        } else {
            if (top.getSymbol() == Operator.LEFT_PARENTHESIS.getSymbol()) {
                s.push(op);
                return;
            }
            ret.append(s.pop().getSymbol()); ret.append(" ");
            var newtop = s.peek();
            operatorRankBehavior(ret, s, newtop, op);
        }
    }

    private static boolean isOperatorOrParenthesis(char c) {
        return Operator.isOperator(c) || c == Operator.LEFT_PARENTHESIS.getSymbol() || c == Operator.RIGHT_PARENTHESIS.getSymbol();
    }

    private static boolean isParenthesis(String s) {
        return s.charAt(0) == Operator.LEFT_PARENTHESIS.getSymbol() ||
               s.charAt(0) == Operator.RIGHT_PARENTHESIS.getSymbol();
    }

    private String[] tokensOf(String s) {
        if (s.equals("")) return new String[]{};
        String[] ret = new String[s.length()];
        int idx = 0;
        for (int i = 0; i < s.length();) {
            var curr = s.charAt(i);
            if (curr == ' ') {
                i++;
                continue;
            }
            StringBuilder tok = new StringBuilder();
            if (isOperatorOrParenthesis(curr)) {
                tok.append(curr);
                ret[idx++] = tok.toString();
                i++;
            } else {
                if (!(i == s.length() - 1)) {
                    do {
                        tok.append(s.charAt(i));
                    } while (!isOperatorOrParenthesis(s.charAt(++i)));
                } else {
                    tok.append(s.charAt(i++));
                }
                ret[idx++] = tok.toString();
            }
        }

        int sanitized_length = 0;
        for (int i = 0; ret[i] != null; ++i) {
            sanitized_length++;
        }
        String[] sanitized_result = new String[sanitized_length];
        for (int i = 0; i < sanitized_length; ++i) {
            sanitized_result[i] = ret[i];
        }

        return sanitized_result;
    }

    public String nextToken(String s, int start) {
        int count = tokensOf(s).length;
        if (start < 0 || start > count - 1) throw new IllegalArgumentException(
                String.format("Make sure start index %d is valid: Must be between 0 - %d", start, count - 1));
        return this.tokensOf(s)[start];
    }

    public boolean isOperand(String s) {
        return !(Operator.isOperator(s) || isParenthesis(s));
    }
}
