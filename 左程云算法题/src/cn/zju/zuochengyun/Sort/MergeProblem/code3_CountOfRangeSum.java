package cn.zju.zuochengyun.Sort.MergeProblem;

/**
 * 区间和的个数
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

 */
public class code3_CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return count(sum, 0, sum.length - 1, lower, upper);
    }

    public int count(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            if (sum[L] >= lower && sum[L] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        int M = L + ((R - L) >> 1);
        return count(sum, L, M, lower, upper) + count(sum, M + 1, R, lower, upper)
                + merge(sum, L, M, R, lower, upper);
    }

    public int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int i = M + 1;
        int ans = 0;
        int windowL = L;
        int windowR = L;
        // [windowL, windowR)
        for (i = M + 1; i <= R; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowR <= M && arr[windowR] <= max) {
                windowR++;
            }
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }
        i = 0;
        long[] help = new long[R - L + 1];
        int p1 = L, p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }
}
