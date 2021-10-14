package cn.zju.leetcode;

public class code14_jianzhiII_69 {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
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
