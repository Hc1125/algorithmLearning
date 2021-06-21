package cn.zju.zuochengyun.ArrayAccumulationSumProblem;

import java.util.HashMap;

/**
 * 数组子数组累加和等于K的最长子数组长度为多少
 * 数组数字可正可负
 * 可转化题目：
 * 题目：一个数组中包含相同1和相同2的子数组的最长长度为多少
 * 转化思路：例如arr = {1,3,4,9,34,34,2,2,1,5}
 * 转化成数组是1即1，是2即-1，其他数字全为0 arr' = {1, 0, 0, 0, 0, 0, -1, -1, 1, 0}
 * 求转化数组累加和为0的最长子数组即为原题解
 */
public class code2_LongestSumSubArrayLength {
    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(i - map.get(sum - k), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }
}
