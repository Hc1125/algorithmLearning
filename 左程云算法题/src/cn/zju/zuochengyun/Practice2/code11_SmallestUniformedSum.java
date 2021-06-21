package cn.zju.zuochengyun.Practice2;

import java.util.Arrays;

/**
 * 在数组最小子集和到数组最大子集和过程中不可以构成的最小数字
 * 例如arr = {1, 3, 4}, minSum = 1, maxSum = 7, 最小不可构成的子集和为2
 * 而arr = {1, 2, 4},则最小不可构成的子集和为8，因为1到7都能构成
 */
public class code11_SmallestUniformedSum {
    // 已知arr中肯定有1这个数字
    // 如果不满足上述这个条件，可以用背包问题思路，去求背包求出来的dp表最后一行第一个是false的sum值
    public static int uniformedSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int range = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > range + 1) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }
}
