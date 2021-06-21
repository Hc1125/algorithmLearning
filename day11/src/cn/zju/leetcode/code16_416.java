package cn.zju.leetcode;

public class code16_416 {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }
        return process(nums, n - 1, target);
    }

    public boolean process(int[] nums, int i, int target) {
        if (target == 0) {
            return true;
        }
        if (i == 0) {
            return nums[i] == target;
        }
        if (nums[i] > target) {
            return process(nums, i - 1, target);
        } else {
            return process(nums, i - 1, target) || process(nums, i - 1, target - nums[i]);
        }
    }

    public boolean dp1(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        dp[0][nums[0]] = true;
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[n - 1][target];
    }

    public boolean dp2(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }

}
