package cn.zju.leetcode;

public class code10_327 {
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

    public int count(long[] sum, int l, int r, int lower, int upper) {
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
