package cn.zju.leetcode;

public class code4_688 {
    public double knightProbability(int n, int k, int row, int column) {
        return process(n, 0, k, row, column) / Math.pow(8, k);
    }
    public int process(int n, int i, int k, int row, int column) {
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }
        if (i == k) {
            return 1;
        }
        int ans = 0;
        ans += process(n, i + 1, k, row + 2, column + 1)
                + process(n, i + 1, k, row - 2, column + 1)
                + process(n, i + 1, k, row + 2, column - 1)
                + process(n, i + 1, k, row - 2, column - 1)
                + process(n, i + 1, k, row + 1, column + 2)
                + process(n, i + 1, k, row - 1, column + 2)
                + process(n, i + 1, k, row + 1, column - 2)
                + process(n, i + 1, k, row - 1, column - 2);
        return ans;
    }

    public double dp(int n, int k, int row, int column) {
        int[][][] dp = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][k] = 1;
            }
        }
        for (int z = k - 1; z >= 0; z--) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j][z] += isVaild(i + 2, j + 1, n) ? dp[i + 2][j + 1][z + 1] : 0;
                    dp[i][j][z] += isVaild(i + 2, j - 1, n) ? dp[i + 2][j - 1][z + 1] : 0;
                    dp[i][j][z] += isVaild(i - 2, j + 1, n) ? dp[i - 2][j + 1][z + 1] : 0;
                    dp[i][j][z] += isVaild(i - 2, j - 1, n) ? dp[i - 2][j - 1][z + 1] : 0;
                    dp[i][j][z] += isVaild(i + 1, j + 2, n) ? dp[i + 1][j + 2][z + 1] : 0;
                    dp[i][j][z] += isVaild(i + 1, j - 2, n) ? dp[i + 1][j - 2][z + 1] : 0;
                    dp[i][j][z] += isVaild(i - 1, j + 2, n) ? dp[i - 1][j + 2][z + 1] : 0;
                    dp[i][j][z] += isVaild(i - 1, j - 2, n) ? dp[i - 1][j - 2][z + 1] : 0;
                }
            }
        }
        return dp[row][column][0];
    }
    public boolean isVaild(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }
}
