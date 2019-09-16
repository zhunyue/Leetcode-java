package Stack;


import java.util.Stack;

/*
    Evaluate the value of an arithmetic expression in Reverse Polish Notation.

    Valid operators are +, -, *, /. Each operand may be an integer or another expression.

    Note:

        Division between two integers should truncate toward zero.
        The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
        Example 1:

            Input: ["2", "1", "+", "3", "*"]
            Output: 9
            Explanation: ((2 + 1) * 3) = 9
 */

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<String> record = new Stack<>();
        for (String str : tokens) {
            if (isNumber(str)) {
                int num = Integer.parseInt(str);
                record.push(str);
            } else {
                int num2 = Integer.parseInt(record.pop());
                int num1 = Integer.parseInt(record.pop());
                if (str.equals("+"))
                    record.push(String.valueOf(num1 + num2));
                else if (str.equals("-"))
                    record.push(String.valueOf(num1 - num2));
                else if (str.equals("*"))
                    record.push(String.valueOf(num1 * num2));
                else
                    record.push(String.valueOf(num1 / num2));
            }
        }
        return Integer.parseInt(record.pop());
    }

    private boolean isNumber(String str) {
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
            return false;
        }
        return true;
    }
}
