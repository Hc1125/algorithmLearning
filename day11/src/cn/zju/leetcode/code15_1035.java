package cn.zju.leetcode;


public class code15_1035 {
    public int maxUncrossedLines(int[] A, int[] B) {
        return process(A, B, A.length - 1, B.length - 1);
    }
    public int process(int[] A, int[] B, int i, int j) {
        if (i == 0 && j == 0) {
            return A[0] == B[0] ? 1 : 0;
        }
        if (i == 0) {
            return A[0] == B[j] ? 1 : process(A, B, 0, j - 1);
        }
        if (j == 0) {
            return A[i] == B[0] ? 1 : process(A, B, i - 1, 0);
        }
        if (A[i] == B[j]) {
            return process(A, B, i - 1, j - 1) + 1;
        } else {
            return Math.max(process(A, B, i - 1,j), process(A, B, i,j - 1));
        }
    }
    public int dp(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m][n];
        dp[0][0] = A[0] == B[0] ? 1 : 0;
        for (int j = 1; j < n; j++) {
            dp[0][j] = A[0] == B[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = A[i] == B[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

}
