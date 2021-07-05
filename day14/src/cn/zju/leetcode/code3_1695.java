package cn.zju.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class code3_1695 {
    public int maximumUniqueSubarray1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0, sum = 0, start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                sum += nums[i];
                set.add(nums[i]);
            } else {
                max = Math.max(sum, max);
                while (nums[i] != nums[start]) {
                    sum -= nums[start];
                    set.remove(nums[start]);
                    start++;
                }
                start++;
            }
        }
        return max = Math.max(sum, max);
    }

    public int maximumUniqueSubarray2(int[] nums) {
        int[] arr = new int[nums.length + 1];
        // 存放数组中的最大值，用来确定标记数组的长度
        int maxLength = 0;
        // 用前缀和数组加速子数组和求解
        for (int i = 0; i < nums.length; i++) {
            arr[i + 1] = arr[i] + nums[i];
            maxLength = Math.max(maxLength, nums[i]);
        }
        int max = 0, start = 0, sum = 0;
        // 用boolean数组代替Set
        boolean[] sign = new boolean[maxLength + 1];
        for (int i = 0; i < nums.length; i++) {
            if (!sign[nums[i]]) {
                sign[nums[i]] = true;
            } else {
                sum = arr[i] - arr[start];
                max = Math.max(max, sum);
                while (start < i && nums[i] != nums[start]) {
                    sign[nums[start]] = false;
                    start++;
                }
                start++;
            }
        }
        sum = arr[nums.length] - arr[start];
        max = Math.max(max, sum);
        return max;
    }
}
