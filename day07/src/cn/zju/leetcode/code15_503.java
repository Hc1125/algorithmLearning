package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class code15_503 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        Arrays.fill(ret, -1);
        for (int i = 0; i < 2 * n - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }
}
