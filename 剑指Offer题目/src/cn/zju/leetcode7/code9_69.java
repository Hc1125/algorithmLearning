package cn.zju.leetcode7;

public class code9_69 {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 1, r = arr.length - 2;
        int m = 0;
        while (l < r) {
            m = l + ((r - l) >> 1);
            if (arr[m] < arr[m + 1]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}
