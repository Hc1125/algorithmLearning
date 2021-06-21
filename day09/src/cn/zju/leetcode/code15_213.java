package cn.zju.leetcode;

import java.util.concurrent.ConcurrentHashMap;

public class code15_213 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int n = nums.length;
        return Math.max(robRangr(nums, 0, n - 2), robRangr(nums, 1, n - 1));
    }

    public int robRangr(int[] nums, int start, int end) {
        int first = nums[start], second = nums[start + 1];
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = first + nums[i];
            first = Math.max(first, temp);
        }
        return Math.max(first, second);
    }
}
