package cn.zju.leetcode1;

public class code3_15 {
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
    public int hammingWeight1(int n) {
        int ans = 0;
        while (n != 0) {
            n &= n - 1;
            ans++;
        }
        return ans;
    }
}
