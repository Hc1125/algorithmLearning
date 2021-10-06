package cn.zju.leetcode;

import java.util.Arrays;

public class code18_1838 {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, ans = 1;
        for (int r = 1; r < n; r++) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l++];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
