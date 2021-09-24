package cn.zju.zuochengyun.Sort.MergeProblem;

/**
 * 区间和的个数
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

 */
public class code3_CountOfRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return count(sum, 0, nums.length - 1, lower, upper);
    }

    /**
     *  arr[l..r]已经不传进来了，只传进来sum（前缀和数组）
     *  在原始的arr[l..r]中，有多少个子数组累加和在[lower, upper]上
     */
    public static int count(int[] sum, int l, int r, int lower, int upper) {
        if (l == r) {
            if (sum[l] >= lower && sum[l] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        int mid = l + ((r - l) >> 1);
        int leftPart = count(sum, l, mid, lower, upper);
        int rightPart = count(sum, mid + 1, r, lower, upper);
        int merge = merge(sum, l, mid, r, lower, upper);
        return leftPart + rightPart + merge;
    }
    public static int merge(int[] sum, int l, int mid, int r, int lower, int upper) {
        int ans = 0;
        int windowL = l;
        int windowR = r;
        for (int i = mid + 1; i <= r; i++) {
            int min = sum[i] - upper;
            int max = sum[i] - lower;
            while (windowR <= mid && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= mid && sum[windowL] <= min) {
                windowL++;
            }
            ans += Math.max(0, windowR - windowL);
        }
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = sum[p1] < sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= mid) {
            help[i++] = sum[p1++];
        }
        while (p2 <= r) {
            help[i++] = sum[p2++];
        }
        return ans;
    }
}
