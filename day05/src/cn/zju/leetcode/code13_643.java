package cn.zju.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class code13_643 {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(sum, maxSum);
        }
        return 1.0 * maxSum / k;
    }

}
