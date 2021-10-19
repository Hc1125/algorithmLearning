package cn.zju.leetcode5;

import java.util.ArrayDeque;
import java.util.Deque;

public class code16_36 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                Integer num2 = stack.pop();
                Integer num1 = stack.pop();
                if (token.equals("+")) {
                    stack.push(num1 + num2);
                } else if (token.equals("-")) {
                    stack.push(num1 - num2);
                } else if (token.equals("*")) {
                    stack.push(num1 * num2);
                } else {
                    stack.push(num1 / num2);
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
