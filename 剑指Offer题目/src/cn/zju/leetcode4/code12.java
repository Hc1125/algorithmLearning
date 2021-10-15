package cn.zju.leetcode4;

import java.util.Arrays;

public class code12 {
    public int pivotIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int sum = Arrays.stream(nums).sum();
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (2 * cur + nums[i] == sum) {
                return i;
            }
            cur += nums[i];
        }
        return -1;
    }
}
