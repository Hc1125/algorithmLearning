package cn.zju.leetcode;

public class code5_88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, index = 0;
        int[] ans = new int[m + n];
        while (i < m && j < n) {
            ans[index++] = nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i < m) {
            ans[index++] = nums1[i++];
        }
        while (j < n) {
            ans[index++] = nums2[j++];
        }
        for (i = 0; i < ans.length; i++) {
            nums1[i] = ans[i];
        }
    }
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
