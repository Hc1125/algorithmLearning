package cn.zju.leetcode;

import java.util.Arrays;

public class code14_552 {
    final static int mod = 1000000007;
    public static int checkRecord(int n) {
        return (int)dp(n);
    }
    public static long process(int index, int a, int l, int n) {
        if (a == 2 || l == 3) {
            return 0;
        }
        if (index == n) {
            return 1;
        }
        long p1 = process(index + 1, a, 0, n);
        long p2 = process(index + 1, a + 1, 0, n);
        long p3 = process(index + 1, a, l + 1, n);
        return (p1 + p2 + p3) % mod;
    }

    public static long dp(int n) {
        long[][] dp = new long[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (i < 2 && j < 3) {
                    dp[i][j] = 1;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            long[][] cur = new long[3][4];
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j + k <= i) {
                        cur[j][k] = (dp[j][0] + dp[j + 1][0] + dp[j][k + 1]) % mod;
                    }
                }
            }
            dp = cur;
        }
        return dp[0][0];
    }

    public static long dp2(int n) {
        int[][] dp = new int[2][3];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int[][] dpNew = new int[2][3];
            for (int j = 0; j <= 1; j++) {
                for (int k = 0; k <= 2; k++) {
                    dpNew[j][0] = (dpNew[j][0] + dp[j][k]) % mod;
                }
            }
            for (int k = 0; k <= 2; k++) {
                dpNew[1][0] = (dpNew[1][0] + dp[0][k]) % mod;
            }
            for (int j = 0; j <= 1; j++) {
                for (int k = 1; k <= 2; k++) {
                    dpNew[j][k] = (dpNew[j][k] + dp[j][k - 1]) % mod;
                }
            }
            dp = dpNew;
        }
        int sum = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sum = (sum + dp[i][j]) % mod;
            }
        }
        return sum;
    }

    // 矩阵快速幂，将二维数组六个数字转成一维数组，六个数字的转化形式表达式是固定的，则可以用矩阵快速幂加速求解
    public static int checkRecord2(int n) {
        long[][] mat = {{1, 1, 0, 1, 0, 0},
                        {1, 0, 1, 1, 0, 0},
                        {1, 0, 0, 1, 0, 0},
                        {0, 0, 0, 1, 1, 0},
                        {0, 0, 0, 1, 0, 1},
                        {0, 0, 0, 1, 0, 0}};
        long[][] res = pow(mat, n);
        long sum = Arrays.stream(res[0]).sum();
        return (int) (sum % mod);
    }

    public static long[][] pow(long[][] mat, int n) {
        long[][] ret = {{1, 0, 0, 0, 0, 0}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, mat);
            }
            n >>= 1;
            mat = multiply(mat, mat);
        }
        return ret;
    }

    public static long[][] multiply(long[][] a, long[][] b) {
        int rows = a.length, columns = b[0].length, temp = b.length;
        long[][] c = new long[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < temp; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                    c[i][j] %= mod;
                }
            }
        }
        return c;
    }

}
