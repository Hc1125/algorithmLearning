package cn.zju.zuochengyun.Practice3;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 给定一个数组arr，再给定一个k值
 * 返回累加和小于等于k，但是离k最近的子数组累加和
 */
public class code5_MaxSubArraySumLessOrEqualK {
    public static int getMaxLessOrEqualK(int[] arr, int K) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (set.ceiling(sum - K) != null) {
                max = Math.max(max, sum - set.ceiling(sum - K));
            }
            set.add(sum);
        }
        return max;
    }
}
