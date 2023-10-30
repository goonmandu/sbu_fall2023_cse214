package cse214hw2;

import java.util.Stack;

public class PostfixEvaluator implements Evaluator {
    public double evaluate(String exp) {
        Stack<String> s = new Stack<>();
        var tokens = tokenizeFromPostfixByWhitespace(exp);
        while (!tokens.isEmpty()) {
            var curr = tokens.pop();
            if (!isOperatorOrParenthesis(curr)) {
                s.push(curr);
            } else {
                if (Operator.isOperator(curr)) {
                    var rhs = s.pop();
                    var lhs = s.pop();
                    s.push(String.valueOf(evaluateBinaryOperator(lhs, curr, rhs)));
                }
            }
        }

        return round2(Double.parseDouble(s.pop()));
    }

    private static boolean isOperatorOrParenthesis(String s) {
        return Operator.isOperator(s) || s.charAt(0) == Operator.LEFT_PARENTHESIS.getSymbol() || s.charAt(0) == Operator.RIGHT_PARENTHESIS.getSymbol();
    }

    private static double round2(double d) {
        return Math.round(d * 100.0) / 100.0;
    }

    private Stack<String> tokenizeFromPostfixByWhitespace(String s) {
        String[] tokens = s.split("\\s+");
        Stack<String> working = new Stack<>();
        Stack<String> ret = new Stack<>();
        for (var e : tokens) {
            working.push(e);
        }
        while (!working.isEmpty()) {
            ret.push(working.pop());
        }
        return ret;
    }

    private Stack<String> tokenizeFromInfix(String s) {
        ToPostfixConverter pfc = new ToPostfixConverter();
        Stack<String> working = new Stack<>();
        Stack<String> ret = new Stack<>();
        try {
            for (int i = 0; i < s.length(); ++i) {
                working.push(pfc.nextToken(s, i));
            }
        } catch (IllegalArgumentException e) { }
        while (!working.isEmpty()) {
            ret.push(working.pop());
        }
        return ret;
    }

    private double evaluateBinaryOperator(String lhs, String op, String rhs) {
        var left = Double.valueOf(lhs);
        var right = Double.valueOf(rhs);
        var result = switch (op) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> left / right;
            default -> throw new IllegalArgumentException(String.format("Operator '%s' is not valid!", op));
        };
        return round2(result);
    }
}
