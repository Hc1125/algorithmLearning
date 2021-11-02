package cn.zju.leetcode7;

public class code12_72 {
    public int mySqrt(int x) {
        int l = 0, r = (int) Math.sqrt(x);
        int ans = 0;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (m * m == x) {
                return m;
            } else if (m * m < x) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }
}
