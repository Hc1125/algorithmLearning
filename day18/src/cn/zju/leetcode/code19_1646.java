package cn.zju.leetcode;

public class code19_1646 {
    public static int getMaximumGenerated(int n) {
        if (n <= 1) {
            return n;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        int ans = Integer.MIN_VALUE;
        for (int i = 2; i <= n; i++) {
            nums[i] = nums[i / 2] + (((i & 1) == 0) ? 0 : nums[i / 2 + 1]);
            ans = Math.max(ans, nums[i]);
        }
        return ans;
    }
}
