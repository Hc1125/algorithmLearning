package cn.zju.leetcode;

import java.util.HashSet;

public class code3_03 {
    public int findRepeatNumber(int[] nums) {
        int[] arr = new int[nums.length];
        for (int num : nums) {
            if (arr[num] == 1) {
                return num;
            } else {
                arr[num] = 1;
            }
        }
        return -1;
    }
}
