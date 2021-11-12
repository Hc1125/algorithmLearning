package cn.zju.leetcode;

public class code15_375 {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                    minCost = Math.min(cost, minCost);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][n];
    }

}
