package cn.zju.leetcode;

public class code18_278 {
    public boolean isBadVersion(int n) {
        return false;
    }
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
