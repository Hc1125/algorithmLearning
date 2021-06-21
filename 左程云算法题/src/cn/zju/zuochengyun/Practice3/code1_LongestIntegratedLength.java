package cn.zju.zuochengyun.Practice3;

import java.util.HashSet;

/**
 * 先给出可整合数组的定义：如果一个数组在排序之后，每相邻两个数差的绝对值都为1，
 * 则该数组为可整合数组。例如，[5,3,4,6,2]排序之后为[2,3,4,5,6]
 * 符合每相邻两个数差的绝对值 都为1，所以这个数组为可整合数组。给定一个整型数组arr
 * 请返回其中最大可整合子数组的长度。例如，[5,5,3,2,6,4,3]的最大可整合子数组为[5,3,2,6,4]，所以返回5
 */
public class code1_LongestIntegratedLength {
    // 转化可整合数组定义：
    // 数组中最大值与最小值的差值等于总长度-1，且没有重复数字，即为可整合数组
    public static int getLIL(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;
        int max = 0;
        int min = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int L = 0; L < arr.length; L++) {
            set.clear();
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int R = L; R < arr.length; R++) {
                if (set.contains(arr[R])) {
                    // 若有重复值，肯定不满足要求
                    break;
                }
                set.add(arr[R]);
                max = Math.max(max, arr[R]);
                min = Math.min(min, arr[R]);
                if (max - min == R - L) {
                    len = Math.max(len, R - L + 1);
                }
            }
        }
        return len;
    }
}
