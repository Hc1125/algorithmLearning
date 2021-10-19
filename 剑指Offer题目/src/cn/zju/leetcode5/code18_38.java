package cn.zju.leetcode5;

import java.util.ArrayDeque;
import java.util.Deque;

public class code18_38 {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        if (n == 1) {
            return new int[]{0};
        }
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                ans[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            ans[stack.pop()] = 0;
        }
        return ans;
    }
}
