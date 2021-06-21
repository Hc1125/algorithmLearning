package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class code4_368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxVal = dp[0];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (maxSize == 1) {
            ans.add(nums[0]);
            return ans;
        }
        for (int i = len - 1; i >= 0 && maxSize > 0; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                ans.add(nums[i]);
                maxSize--;
                maxVal = nums[i];
            }

        }
        return ans;
    }
}
