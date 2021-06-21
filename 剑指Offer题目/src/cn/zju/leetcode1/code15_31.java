package cn.zju.leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;

public class code15_31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        if (n == 0) {
            return true;
        }
        int i = 0, j = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        while (i < n && j <n) {
            while ((stack.isEmpty() || stack.peekLast() != popped[j]) && (i < n)) {
                stack.addLast(pushed[i++]);
            }
            while ((!stack.isEmpty()) && (stack.peekLast() == popped[j])) {
                stack.pollLast();
                j++;
            }
        }
        return i == n && j == n;
    }
    public boolean validateStackSequences1(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
