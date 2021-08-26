package cn.zju.zuochengyun.Practice4;

import java.util.Arrays;

/**
 * 给定一个数组arr，长度为N且每个值都是正数，代表N个人的体重。再给定个正数limit,代表一艘船的载重。
 * 以下是坐船规则:
 * 1)每艘船最多只能做两人;
 * 2)乘客的体重和不能超过limit
 * 返回如果同时让这N个人过河最少需要几条船。
 */
public class code4_MinBoat {
    // 最优解
    public static int minBoat1(int[] arr, int limit) {
        Arrays.sort(arr);
        int light = 0, heavy = arr.length - 1;
        int ans = 0;
        while (light <= heavy) {
            if (arr[light] + arr[heavy] <= limit) {
                light++;
            }
            heavy--;
            ans++;
        }
        return ans;
    }
    // 请保证arr有序
    public static int minBoat2(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr[arr.length - 1] > limit) {
            return -1;
        }
        int lessR = -1;
        // 所有人的体重都不超过limit，继续讨论
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= (limit / 2)) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) {
            return arr.length;
        }
        // 一定有左右两个部分，左(<= limit / 2) 右(> limit / 2)
        int L = lessR;
        int R = lessR + 1;
        int noUsed = 0;  // 画X的数量统计，画对号的量（加工出来的）
        while (L >= 0) {
            int solved = 0;  // 此时的[L]，让R画过了几个数
            while (R < arr.length && arr[L] + arr[R] <= limit) {
                R++;
                solved++;
            }
            // R来到的位置是又不达标的位置
            if (solved == 0) {
                noUsed++;
                L--;
            } else {
                // 此时的[L],让R画过了solved(>0)个数
                L = Math.max(-1, L - solved);
            }
        }
        int all = lessR + 1; // 左半区总个数  <= limit / 2 的区域
        int used = all - noUsed; // 画对号的量
        int moreUnsolved = arr.length - all - used;   // > limit / 2区中，没搞定的数量
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }
}
