package cn.zju.leetcode;

public class code18_122 {
    private static int maxProfit1(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
    private static int maxProfit2(int[] prices){
        int n = prices.length;
        int profit1 = 0;
        int profit2 = -prices[0];
        for (int i = 1; i < n; i++) {
            int newProfit1 = Math.max(profit1,profit2+prices[i]);
            int newProfit2 = Math.max(profit1 - prices[i],profit2);
            profit1 = newProfit1;
            profit2 = newProfit2;
        }
        return profit1;
    }
    private static int maxProfit3(int[] prices){
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            if(prices[i] > prices[i-1]){
                ans += prices[i] - prices[i-1];
            }
        }
        return ans;
    }
}
