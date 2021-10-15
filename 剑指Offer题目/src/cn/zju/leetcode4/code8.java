package cn.zju.leetcode4;

public class code8 {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int l = 0, r = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        while (r < n) {
            sum += nums[r++];
            while (sum >= target) {
                ans = Math.min(ans, r - l);
                sum -= nums[l++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
