package cn.zju.leetcode;

public class code5_377 {
    public int ans;
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == target ? 1 : 0;
        }
        ans = 0;
        process(nums, target, 0);
        return ans;
    }

    public void process(int[] nums, int target, int cur) {
        if (cur > target) {
            return;
        } else if (cur == target) {
            ans++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            process(nums, target, cur + nums[i]);
        }
    }

    public int combinationSum5(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}
