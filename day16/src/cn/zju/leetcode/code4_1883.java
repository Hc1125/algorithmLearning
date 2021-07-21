package cn.zju.leetcode;

import java.util.Arrays;

public class code4_1883 {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 1;
        long total = 0;
        int l = 0;
        for (int r = 1; r < n; r++) {
            total += (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
