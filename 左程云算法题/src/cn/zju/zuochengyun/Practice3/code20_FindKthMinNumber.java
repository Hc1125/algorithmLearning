package cn.zju.zuochengyun.Practice3;

/**
 * 给定两个整数数组A和B
 * A是长度为m、元素从小到大排好序了
 * B是长度为n、元素从小到大排好序了
 * 希望从A和B数组中，找出第k大的数字
 */
public class code20_FindKthMinNumber {
    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        if (arr1 == null || arr2 == null) {
            return -1;
        }
        if (kth < 1 || kth > arr1.length + arr2.length) {
            return -1;
        }
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        if (kth <= s) {
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        if (kth > l) {
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }
        // 短数组长度 < k <= 长数组长度
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }

    // A[s1...e1]
    // B[s2...e2]
    // 这两段一定等长且有序
    // 求这两段整体的上中位数，上中位数值返回
    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        int mid1 = 0, mid2 = 0;
        int offset = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            offset = ((e1 - s1 + 1) & 1) ^ 1;  // 偶数等于1，奇数等于0
            if (A[mid1] > B[mid2]) {
                e1 = mid1;
                s2 = mid2 + offset;
            } else if (A[mid1] < B[mid2]) {
                s1 = mid1 + offset;
                e2 = mid2;
            } else {
                // 两段各自上中点的数，是一样大的时候
                return A[mid1];
            }
        }
        return Math.min(A[s1], B[s2]);
    }
}
