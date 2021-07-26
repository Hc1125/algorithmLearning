package cn.zju.zuochengyun.Practice2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给定一个有序的正整数数组arr和一个正数range，如果可以自由选择arr中的数字
 * 想累加得到1~range范围上所以的数字，返回arr最少还缺几个数字
 * 举例：
 * arr = {1,2,3,7}, range = 15
 * 想累加得到1~15范围上的所有数字，arr还缺14这个数，所以返回1
 */
public class code12_MinPatches {
    public static int minPatches(int[] arr, int aim) {
        int patches = 0;
        long range = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            // 1 ~ range
            // 1 ~ arr[i] - 1
            while (arr[i] - 1 > range) {
                range += range + 1;
                patches++;
                if (range >= aim) {
                    return patches;
                }
            }
            range += arr[i];
            if (range >= aim) {
                return patches;
            }
        }
        while (aim >= range + 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }
}
