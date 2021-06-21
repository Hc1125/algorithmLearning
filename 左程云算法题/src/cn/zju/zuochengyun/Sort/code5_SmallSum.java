package cn.zju.zuochengyun.Sort;

public class code5_SmallSum {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0 , arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L ) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);
    }
    public static int merge(int[] arr, int l, int m, int r) {
        int ans = 0;
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            if (arr[p1] < arr[p2]) {
                help[i++] = arr[p1];
                ans += arr[p1] * (r - p2 + 1);
                p1++;
            } else {
                help[i++] = arr[p2++];
            }
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return ans;
    }
}
