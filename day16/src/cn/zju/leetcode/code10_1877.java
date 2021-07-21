package cn.zju.leetcode;

import java.util.Arrays;

public class code10_1877 {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        int i = 0, j = n - 1;
        int ans = Integer.MIN_VALUE;
        Arrays.sort(nums);
        while (i < j) {
            ans = Math.max(ans, nums[i++] + nums[j--]);
        }
        return ans;
    }
}
