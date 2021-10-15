package cn.zju.leetcode;

import java.util.LinkedList;

public class code16_jianzhiII_38 {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        if (n == 1) {
            return new int[]{0};
        }
        int[] ans = new int[n];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peekLast()] < temperatures[i]) {
                ans[stack.peekLast()] = i - stack.pollLast();
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            ans[stack.pollLast()] = 0;
        }
        return ans;
    }
}
