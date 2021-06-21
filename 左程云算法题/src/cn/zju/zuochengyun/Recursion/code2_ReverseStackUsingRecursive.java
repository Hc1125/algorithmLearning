package cn.zju.zuochengyun.Recursion;

import java.util.ArrayDeque;
import java.util.Deque;

public class code2_ReverseStackUsingRecursive {
    public static void reverse(Deque<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }
    // 将栈底元素弹出
    public static int f(Deque<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(1);
        stack.addLast(2);
        stack.addLast(3);
        stack.addLast(4);
        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pollLast());
        }
    }
}
