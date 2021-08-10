package cn.zju.leetcode;

import java.util.ArrayList;
import java.util.List;

public class code4_413 {
    public int numberOfArithmeticSlices1(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int[] ints = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            ints[i] = nums[i + 1] - nums[i];
        }
        List<Integer> list = new ArrayList<>();
        int length = 1;
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] == ints[i - 1]) {
                length++;
            } else {
                if (length >= 2) {
                    list.add(length + 1);
                }
                length = 1;
            }
        }
        if (length >= 2) {
            list.add(length + 1);
        }
        int ans = 0;
        for (int len : list) {
            ans += (len - 1) * (len - 2) / 2;
        }
        return ans;
    }
    public int numberOfArithmeticSlices2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int d = nums[0] - nums[1], t = 0;
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i - 1] - nums[i] == d) {
                t++;
            } else {
                d = nums[i - 1] - nums[i];
                t = 0;
            }
            ans += t;
        }
        return ans;
    }
}
