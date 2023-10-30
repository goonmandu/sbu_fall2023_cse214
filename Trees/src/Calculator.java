public class Calculator extends BinaryTree<String> {
    public Calculator() { }
    public Calculator(String s) { }

    public static double evaluateTwoOperands(double left, String o, double right) {
        switch (o) {
            case "+": return left + right;
            case "-": return left - right;
            case "/": return left / right;
            case "*": return left * right;
            default: throw new IllegalArgumentException(String.format("%s is not a valid operator!", o));
        }
    }

    public static boolean isOperator(String e) {
        return (   e.equals("+")
                || e.equals("-")
                || e.equals("/")
                || e.equals("*"));
    }

    public static boolean isNumber(String e) {
        return !isOperator(e);
    }

    public double evaluate() {
        if (e == null) {
            return 0;  // Default zero
        }
        if (isNumber(e)) {
            return Double.parseDouble(e);
        }
        double leftvalue = ((Calculator) this.left).evaluate();
        double rightvalue = ((Calculator) this.right).evaluate();
        return evaluateTwoOperands(leftvalue, e, rightvalue);
    }

    public static void main(String[] args) {
        Calculator exp = new Calculator();
        exp.e = "+";
        exp.left = new Calculator();
        exp.left.e = "*";
        exp.left.left = new Calculator();
        exp.left.right = new Calculator();
        exp.left.left.e = "4";
        exp.left.right.e = "6";
        exp.right = new Calculator();
        exp.right.e = "/";
        exp.right.left = new Calculator();
        exp.right.right = new Calculator();
        exp.right.left.e = "9";
        exp.right.right.e = "-";
        exp.right.right.left = new Calculator();
        exp.right.right.right = new Calculator();
        exp.right.right.left.e = "10";
        exp.right.right.right.e = "14";
        System.out.println(exp.evaluate());

    }
}
