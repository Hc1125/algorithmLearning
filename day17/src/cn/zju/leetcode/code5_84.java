package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class code5_84 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] >= heights[i]) {
                right[stack.pollLast()] = i;
            }
            left[i] = stack.isEmpty() ? -1 : stack.peekLast();
            stack.addLast(i);
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, heights[i] * (right[i] - left[i] - 1));
        }
        return ans;
    }
}
