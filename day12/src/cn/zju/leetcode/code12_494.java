package cn.zju.leetcode;

import java.util.Arrays;

public class code12_494 {
    public int findTargetSumWays(int[] nums, int target) {
        return process(nums, target, 0, 0);
    }
    public int process(int[] nums, int target, int i, int sum) {
        if (i == nums.length) {
            return sum == target ? 1 : 0;
        }
        return process(nums, target, i + 1, sum - nums[i]) + process(nums, target, i + 1, sum + nums[i]);
    }
    public int findTargetSumWays1(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if ((sum - target) % 2 != 0 || sum < target) {
            return 0;
        }
        int neg = (sum - target) >> 1;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }
}
