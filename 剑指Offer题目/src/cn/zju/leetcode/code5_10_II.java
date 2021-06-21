package cn.zju.leetcode;

public class code5_10_II {
    public int numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return numWays(n - 1) + numWays(n - 2);
    }
    public int numWays1(int n) {
        int a = 1;
        int b = 1;
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum = (a + b) % (int)(1e9 + 7);
            a = b;
            b = sum;
        }
        return b;
    }
}
