package cn.zju.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class code15_740 {
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] sum = new int[max + 1];
        for (int num : nums) {
            sum[num] += num;
        }
        int first = sum[0], second = Math.max(sum[0], sum[1]);
        for (int i = 2; i <= max; i++) {
            int temp = second;
            second = Math.max(first + sum[i], second);
            first = temp;
        }

        return second;
    }
}
