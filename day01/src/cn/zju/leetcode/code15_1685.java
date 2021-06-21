package cn.zju.leetcode;


import java.util.concurrent.ThreadPoolExecutor;

public class code15_1685 {
    private int[] getSumAbsoluteDifference1(int[] nums){
        int n = nums.length;
        int[] result = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        int su = 0;
        for (int i = 0; i < n; i++) {
            result[i] = sum - n*nums[i] + (nums[i] * i - su) * 2;
            su += nums[i];
        }
        return result;
    }
    private int[] getSumAbsoluteDifference2(int[] nums){
        int len = nums.length;
        int[] ans = new int[len];
        for (int i = 1; i < len; i++) {
            ans[0] += (nums[i] - nums[0]);
        }
        for (int i = 1; i < len; i++) {
            int n = nums[i] - nums[i-1];
            ans[i] = ans[i-1] + (2*i -len) * n;
        }
        return ans;
    }
}
