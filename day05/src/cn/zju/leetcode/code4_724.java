package cn.zju.leetcode;

import java.util.Arrays;

public class code4_724 {
    public int pivotIndex(int[] nums){
        int n = nums.length;
        if(n == 0){
            return -1;
        }
        if(n == 1){
            return 0;
        }
        int left = 0;
        int right = 0;
        for (int i = 1; i < n; i++) {
            right += nums[i];
        }
        for (int i = 0; i < n - 1; i++) {
            if(left == right){
                return i;
            } else {
                left += nums[i];
                right -= nums[i + 1];
            }
        }
        if(left == 0){
            return n - 1;
        }
        return -1;
    }
    public int privotIndex1(int[] nums){
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if(2 * sum + nums[i] == total){
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}
