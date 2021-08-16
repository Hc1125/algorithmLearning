package cn.zju.group2;

import java.util.ArrayDeque;
import java.util.Deque;

public class code7_150 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String str : tokens) {
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                compute(stack, str);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.peek();
    }

    public void compute(Deque<Integer> stack, String op) {
        int num2 = stack.pop();
        int num1 = stack.pop();
        int ans = 0;
        switch (op) {
            case "+":
                ans = num1 + num2;
                break;
            case "-":
                ans = num1 - num2;
                break;
            case "*":
                ans = num1 * num2;
                break;
            case "/":
                ans = num1 / num2;
                break;
        }
        stack.push(ans);
    }
}
