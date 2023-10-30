package cse214hw2;

import java.util.Stack;

public class BalancedWord {
    private final String word;
    public BalancedWord(String word) {
        if (isBalanced(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a balanced word.", word));
    }
    public static boolean isBalanced(String word) {
        Stack<Character> parentheses = new Stack<>();
        for (var c : word.toCharArray()) {
            if (c == Operator.LEFT_PARENTHESIS.getSymbol()) {
                parentheses.push(c);
            } else if (c == Operator.RIGHT_PARENTHESIS.getSymbol()) {
                if (parentheses.isEmpty()) return false;
                parentheses.pop();
            }
        }
        return parentheses.isEmpty();
    }
    public String getWord() { return word; }
}
